import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerLogsContainer
import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerPullImage
import net.futureset.kontroldb.DbDockerExtension

plugins {
    id("com.bmuschko.docker-remote-api")
}

val dockerExtension = extensions.create<DbDockerExtension>("dbdocker")
val inCi = System.getenv()["CI"] == "true"
dockerExtension.dockerEnabled = !inCi
dockerExtension.containerName = project.name

val downloadImage = tasks.register<DockerPullImage>("downloadImage") {
    description = "Downloads the docker image for the integration test"
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    image = dockerExtension.imageId
}

val createServer = tasks.register<DockerCreateContainer>("createServer") {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    containerName.convention(dockerExtension.containerName)
    hostName.convention(dockerExtension.containerName)
    envVars.convention(dockerExtension.envProperties)
    imageId.convention(dockerExtension.imageId)
    cmd.convention(dockerExtension.commandLine)
    tty = true
    hostConfig.portBindings.convention(
        dockerExtension.internalToExternalPortMap.map { f -> f.entries.map { "${it.value}:${it.key}" } },
    )
    hostConfig.binds.put(
        project.layout.buildDirectory
            .get()
            .toString(),
        "/var/outputdir",
    )
    hostConfig.binds.putAll(dockerExtension.mountPoints)
    hostConfig.autoRemove = true
    exposedPorts.convention(
        dockerExtension.internalToExternalPortMap
            .map {
                DockerCreateContainer.ExposedPort(
                    "tcp",
                    it.keys.toList(),
                )
            }.flatMap { ports -> project.objects.listProperty(DockerCreateContainer.ExposedPort::class).also { it.addAll(ports) } },
    )
    dependsOn(downloadImage)
}

val removeServer =
    tasks.register<DockerRemoveContainer>("removeServer") {
        description = "Removes the docker image for the integration test"
        enabled = dockerExtension.dockerEnabled.get()
        group = "docker"
        targetContainerId(dockerExtension.containerName)
    }

val startServer = tasks.register<DockerStartContainer>("startServer") {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    description = "Starts the docker image for the integration test"
    dependsOn(createServer)
    targetContainerId(dockerExtension.containerName)
    onNext {
    }
}

val logContainer = tasks.register<DockerLogsContainer>("logContainer") {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    description = "Logs the docker image for the integration test"
    outputs.upToDateWhen { false }
    dependsOn(startServer)
    targetContainerId(dockerExtension.containerName)
    follow = true
    tailAll = true
    stdErr = false
    val logMessage = dockerExtension.waitForStartupLogMessage
    onNext {
        if (logMessage.isPresent &&
            this
                .toString()
                .contains(logMessage.get())
        ) {
            throw StopActionException("Started OK")
        } else {
            Thread.sleep(10)
        }
    }
    onError {
        if (logMessage.isPresent && message != "Started OK") {
            throw this
        }
    }
}

val stopServer = tasks.register<DockerStopContainer>("stopServer") {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    description = "Stops the docker image for the integration test"
    targetContainerId(dockerExtension.containerName)
    onError {
        logger.info("Stopped already")
    }
}

tasks.named<Test>("integrationTest") {
    if (inCi) {
        systemProperty("shareddir", "/home/runner/work")
    }
    dependsOn(logContainer)
    finalizedBy(stopServer, "jacocoIntegrationTestReport")
}

tasks.named("clean") {
    dependsOn(stopServer)
}

tasks.register("showdockercommandline") {
    group = "docker"
    description = "prints docker command line to console"

    doLast {
        println(
            (
                listOf(
                    "docker",
                    "run",
                    "-d",
                    "-it",
                    "--name",
                    dockerExtension.containerName.get(),
                    "-v",
                    "${project.layout.buildDirectory.get()}/:/var/outputdir",
                ) +
                    dockerExtension.internalToExternalPortMap
                        .map { f -> f.entries.map { "-p ${it.value}:${it.key}" } }
                        .get() +
                    dockerExtension.envProperties
                        .get()
                        .entries
                        .map { "-e ${it.key}=${it.value}" } +
                    listOf(dockerExtension.imageId.getOrElse("specify-image-id-here"))
                ).joinToString(" "),
        )
    }
}

tasks.register("showgithubcontainerconfig") {
    group = "docker"
    description = "prints github container service to console"

    doLast {
        println(
            """
        ${dockerExtension.containerName.get()}:
            image: ${dockerExtension.imageId.getOrElse("specify-image-id-here")}
            volumes:
              - /home/runner/work:/var/outputdir
            options: >-
              --name ${dockerExtension.containerName.get()}
            ports:
${createServer.get().hostConfig.portBindings.get().joinToString(separator = "\n") { "              - $it" }}
            env:
${
                createServer.get().envVars.get().entries.joinToString(
                    separator = "\n",
                    postfix = "\n",
                ) { "              ${it.key}: '${it.value}'" }
            }
            """.trimIndent(),
        )
    }
}

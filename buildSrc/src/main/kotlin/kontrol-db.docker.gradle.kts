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

val downloadImage by tasks.registering(DockerPullImage::class) {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    image = dockerExtension.imageId
}

val createServer by tasks.registering(DockerCreateContainer::class) {
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
    hostConfig.binds.put(project.layout.buildDirectory.get().toString(), "/var/outputdir")
    hostConfig.binds.putAll(dockerExtension.mountPoints)
    hostConfig.autoRemove = true
    exposedPorts.convention(
        dockerExtension.internalToExternalPortMap.map {
            DockerCreateContainer.ExposedPort(
                "tcp",
                it.keys.toList(),
            )
        }.flatMap { ports -> project.objects.listProperty(DockerCreateContainer.ExposedPort::class).also { it.addAll(ports) } },
    )
    dependsOn(downloadImage)
}

val removeServer = tasks.registering(DockerRemoveContainer::class) {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    targetContainerId(dockerExtension.containerName)
}

val startServer by tasks.registering(DockerStartContainer::class) {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    dependsOn(createServer)
    targetContainerId(dockerExtension.containerName)
    onNext {
    }
}

val logContainer by tasks.registering(DockerLogsContainer::class) {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    outputs.upToDateWhen { false }
    dependsOn(startServer)
    targetContainerId(dockerExtension.containerName)
    follow = true
    tailAll = true
    stdErr = false
    onNext {
        if (dockerExtension.waitForStartupLogMessage.isPresent && this.toString()
                .contains(dockerExtension.waitForStartupLogMessage.get())
        ) {
            throw StopActionException("Started OK")
        } else {
            Thread.sleep(10)
        }
    }
    onError {
        if (dockerExtension.waitForStartupLogMessage.isPresent && message != "Started OK") {
            throw this
        }
    }
}

val stopServer by tasks.registering(DockerStopContainer::class) {
    enabled = dockerExtension.dockerEnabled.get()
    group = "docker"
    targetContainerId(dockerExtension.containerName)
    onError {
        project.logger.info("Stopped already")
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
                    dockerExtension.internalToExternalPortMap.map { f -> f.entries.map { "-p ${it.value}:${it.key}" } }
                        .get() +
                    dockerExtension.envProperties.get().entries.map { "-e ${it.key}=${it.value}" } +
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

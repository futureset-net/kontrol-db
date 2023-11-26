
import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerLogsContainer
import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerPullImage

plugins {
    id("kontrol-db.database.setup")
    alias(libs.plugins.docker)
}

description = "sqlserver extensions for kontrol-db"

dependencies {
    api(libs.sqlserver)
}

val inCi = System.getenv("CI") == "true"
val dockerEnabled = !inCi

val sqlServerContainerName = "mssql"

val downloadImage by tasks.registering(DockerPullImage::class) {
    enabled = dockerEnabled
    group = "docker"
    image = "mcr.microsoft.com/mssql/server:2022-latest"
}

val createServer by tasks.registering(DockerCreateContainer::class) {
    enabled = dockerEnabled
    group = "docker"
    containerName = sqlServerContainerName
    hostName = sqlServerContainerName
    envVars.put("ACCEPT_EULA", "Y")
    envVars.put("MSSQL_SA_PASSWORD", "Th1sIsW0rking")
    imageId = "mcr.microsoft.com/mssql/server:2022-latest"
    hostConfig.portBindings.add("6283:1433")
    hostConfig.binds.put(project.layout.buildDirectory.get().toString(), "/var/outputdir")
    hostConfig.autoRemove = true
    exposePorts("tcp", listOf(6283))
    dependsOn(downloadImage)
}

val removeServer = tasks.registering(DockerRemoveContainer::class) {
    enabled = dockerEnabled
    group = "docker"
    targetContainerId(sqlServerContainerName)
}

val startServer by tasks.registering(DockerStartContainer::class) {
    enabled = dockerEnabled
    group = "docker"
    dependsOn(createServer)
    targetContainerId(sqlServerContainerName)
    onNext {
    }
}

val logContainer by tasks.registering(DockerLogsContainer::class) {
    enabled = dockerEnabled
    outputs.upToDateWhen { false }
    dependsOn(startServer)
    targetContainerId(sqlServerContainerName)
    follow = true
    tailAll = true
    stdErr = false
    onNext {
        if (this.toString().contains("SQL Server is now ready for client connections")) {
            throw StopActionException("Started OK")
        }
    }
    onError {
        if (message != "Started OK") {
            throw this
        }
    }
}

val stopServer by tasks.registering(DockerStopContainer::class) {
    enabled = dockerEnabled
    group = "docker"
    targetContainerId(sqlServerContainerName)
    onError {
        println("Stopped already")
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

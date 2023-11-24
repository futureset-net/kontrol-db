
import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerLogsContainer
import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerPullImage
import java.net.URI

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.docker)
    `maven-publish`
}

description = "sqlserver extensions for kontrol-db"

sourceSets {
    getByName("main") {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

dependencies {
    ksp(libs.koin.compiler)

    api(project(":kontrol-db-core"))
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

testing {
    suites {
        register<JvmTestSuite>("integrationTest") {
            sources {
                kotlin {
                    srcDir(project.layout.buildDirectory.dir("generated/integrationTest/kotlin"))
                }
            }
            useJUnitJupiter(rootProject.libs.versions.junit)
            testType = TestSuiteType.INTEGRATION_TEST
            dependencies {
                implementation(rootProject.libs.junit.mockito)
                implementation(rootProject.libs.assertj)
                implementation(project(":integrationTest"))
                implementation(project(":kontrol-db-core"))
                implementation(project())
            }
            targets {
                all {
                    testTask.configure {
                        dependsOn(logContainer)
                        finalizedBy(stopServer, "jacocoIntegrationTestReport")
                        if (inCi) {
                            systemProperty("shareddir", "/home/runner/work")
                        }
                    }
                }
            }
        }
    }
}

tasks.named("clean") {
    dependsOn(stopServer)
}

val jacocoIntegrationTestReport by tasks.registering(JacocoReport::class) {
    group = "verification"
    dependsOn("integrationTest")
    this.sourceSets(project.sourceSets["main"], project(":kontrol-db-core").sourceSets["main"])
    executionData(tasks.getByPath("integrationTest"))
}

publishing {
    publications {
        create<MavenPublication>("mavenPublication") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI.create("https://maven.pkg.github.com/futureset/kontrol-db")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.check {
    dependsOn("integrationTest")
}

val copySharedTests by tasks.registering(Copy::class) {
    from(project(":integrationTest").layout.projectDirectory.dir("src/test/kotlin"))
    into(project.layout.buildDirectory.dir("generated/integrationTest/kotlin"))
}

tasks.whenTaskAdded {
    if (name == "kspIntegrationTestKotlin") {
        dependsOn(copySharedTests)
    }
}

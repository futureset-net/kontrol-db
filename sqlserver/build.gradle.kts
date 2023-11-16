
import com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer
import com.bmuschko.gradle.docker.tasks.container.DockerLogsContainer
import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStartContainer
import com.bmuschko.gradle.docker.tasks.container.DockerStopContainer
import com.bmuschko.gradle.docker.tasks.image.DockerPullImage

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.docker)
}

sourceSets {
    getByName("main") {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

dependencies {
    ksp(libs.koin.compiler)
    implementation(project(":core"))
    api(libs.sqlserver)
}

val inCi = System.getenv("CI") == "true"
val dockerEnabled = !inCi

val sqlServerContainerName = "kontrol-sqlserver"

val downloadImage by tasks.registering(DockerPullImage::class) {
    enabled = dockerEnabled
    group = "docker"
    image = "mcr.microsoft.com/mssql/server:2022-latest"
}

tasks.register<DockerCreateContainer>("create-server") {
    enabled = dockerEnabled
    group = "docker"
    containerName = sqlServerContainerName
    hostName = sqlServerContainerName
    envVars.put("ACCEPT_EULA", "Y")
    envVars.put("MSSQL_SA_PASSWORD", "Th1sIsW0rking")
    imageId = "mcr.microsoft.com/mssql/server:2022-latest"
    hostConfig.portBindings.add("6283:1433")
    hostConfig.binds.put(rootProject.layout.projectDirectory.toString(), "/git/workspace")
    hostConfig.autoRemove = true
    exposePorts("tcp", listOf(6283))
    dependsOn(downloadImage)
}

tasks.register<DockerRemoveContainer>("remove-server") {
    enabled = dockerEnabled
    group = "docker"
    targetContainerId(sqlServerContainerName)
}

tasks.register<DockerStartContainer>("start-server") {
    enabled = dockerEnabled
    group = "docker"
    dependsOn("create-server")
    targetContainerId(sqlServerContainerName)
    onNext {
    }
}

tasks.register<DockerLogsContainer>("log-container") {
    enabled = dockerEnabled
    outputs.upToDateWhen { false }
    dependsOn("start-server")
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

tasks.register<DockerStopContainer>("stop-server") {
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
                implementation(project(":core"))
                implementation(project())
            }
            targets {
                all {
                    testTask.configure {
                        dependsOn("log-container")
                        finalizedBy("stop-server", "jacocoIntegrationTestReport")
                        testLogging {
                            showStandardStreams = true
                        }
                        systemProperty("shareddir", if (inCi) "/home/runner/work" else project.layout.buildDirectory.get().toString())
                    }
                }
            }
        }
    }
}

tasks.named("clean") {
    dependsOn("stop-server")
}
val jacocoIntegrationTestReport by tasks.registering(JacocoReport::class) {
    group = "verification"
    dependsOn("integrationTest")
    this.sourceSets(project.sourceSets["main"], project(":core").sourceSets["main"])
    executionData(tasks.getByPath("integrationTest"))
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

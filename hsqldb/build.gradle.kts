plugins {
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":core"))
    ksp(libs.koin.compiler)
    api(libs.hsqldb)
    api(libs.hsqldb.tool)
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
                        finalizedBy("jacocoIntegrationTestReport")
                    }
                }
            }
        }
    }
}

val jacocoIntegrationTestReport by tasks.registering(JacocoReport::class) {
    group = "verification"
    this.sourceSets(project.sourceSets["main"], project(":core").sourceSets["main"])
    executionData(tasks.getByPath("integrationTest"))
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

sourceSets {
    getByName("main") {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

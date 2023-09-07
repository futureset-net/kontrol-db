import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    alias(libs.plugins.spotless)
}

group = "net.futureset"
version = "local-SNAPSHOT"

extensions.configure<SpotlessExtension> {
    format("misc") {
        // define the files to apply `misc` to
        target("*.md", ".gitignore")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    kotlin {
        // version, userData and editorConfigOverride are all optional
        ktlint(libs.versions.ktlint.get())
        target("**/src/*/kotlin/**/*.kt", "**/*.gradle.kts")
    }
}

subprojects {
    apply(plugin = "jacoco")
    apply(plugin = "kotlin")

    extensions.configure<JacocoPluginExtension> {
        toolVersion = rootProject.libs.versions.jacoco.get()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
    testing {
        suites {
            val test by getting(JvmTestSuite::class) {
                useJUnitJupiter(rootProject.libs.versions.junit)
                dependencies {
                    implementation(rootProject.libs.junit.mockito)
                    implementation(rootProject.libs.assertj)
                }
                targets {
                    all {
                        testTask.configure {
                            finalizedBy("jacocoTestReport")
                        }
                    }
                }
            }
        }
    }

    val unitTestCoverageLimit: String by project

    tasks.existing(JacocoCoverageVerification::class) {
        executionData(tasks.test.get())
        dependsOn(tasks.test)
        violationRules {
            rule {
                limit {
                    minimum = "1.0".toBigDecimal()
                }
            }
        }
    }

    tasks.named("check") {
        dependsOn("jacocoTestCoverageVerification")
    }

    tasks.processResources {
        dependsOn(rootProject.tasks.named("spotlessApply"))
    }

    tasks.withType<JavaCompile>().configureEach {
        enabled = false
    }

    dependencies {

        testImplementation(enforcedPlatform(rootProject.libs.mockito.bom))
        testImplementation(enforcedPlatform(rootProject.libs.junit.bom))

        project(":core")
        testImplementation(rootProject.libs.bundles.junit5) {
            exclude(group = "org.hamcrest")
        }
    }
}

val verGradle: String by project

tasks.wrapper {
    gradleVersion = verGradle
}

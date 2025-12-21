@file:Suppress("UnstableApiUsage")

import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    kotlin("jvm").apply(false)
    alias(libs.plugins.spotless)
    id("org.jetbrains.dokka")
    base
    id("jacoco-report-aggregation")
}

println("VERSION is $version")

extensions.configure<SpotlessExtension> {
    format("misc") {
        // define the files to apply `misc` to
        target("*.md", ".gitignore")

        trimTrailingWhitespace()
        leadingTabsToSpaces()
        endWithNewline()
    }
    kotlin {
        // version, userData and editorConfigOverride are all optional
        ktlint().editorConfigOverride(
            mapOf(
                "ktlint_standard_comment-wrapping" to "disabled",
                "max_line_length" to 2147483647,
            ),
        )
        target("*/src/*/kotlin/**/*.kt", "*.gradle.kts", "*/*.kts", "buildSrc/src/**/*.kts")
    }
}

val referenceToLibs = libs
allprojects {
    pluginManager.withPlugin("java") {
        configure<JavaPluginExtension> {
            toolchain {
                languageVersion = JavaLanguageVersion.of(referenceToLibs.versions.java.get())
            }
        }
    }
}

dokka {
    moduleName.set(project.name)
    dokkaSourceSets.configureEach {
        includes.from(project.layout.projectDirectory.file("module.md"))
    }
    this.basePublicationsDirectory = layout.buildDirectory.dir("docs")
}

subprojects {
    apply(plugin = "jacoco")
    apply(plugin = "kotlin")

    jacoco {
        toolVersion = referenceToLibs.versions.jacoco.get()
    }

    tasks.named<Jar>("jar") {
        manifest {
            attributes(
                "Implementation-Title" to (project.description ?: project.name),
                "Implementation-Version" to project.version,
                "Implementation-Vendor" to "futureset.net",
            )
        }
    }

    configure<TestingExtension> {
        suites {
            val test by getting(JvmTestSuite::class) {
                useJUnitJupiter(rootProject.libs.versions.junit)
                dependencies {
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

    tasks.withType<Test> {
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    tasks.named("check") {
        dependsOn("jacocoTestCoverageVerification")
    }

    tasks.named("processResources") {
        dependsOn(rootProject.tasks.named("spotlessApply"))
    }

    tasks.withType<JavaCompile>().configureEach {
        enabled = false
    }

    dependencies {

        "testImplementation"(enforcedPlatform(rootProject.libs.junit.bom))

        project(":kontrol-db-core")
        "testImplementation"(rootProject.libs.bundles.junit5) {
            exclude(group = "org.hamcrest")
        }
    }
}

dependencies {
    dokka(project(":kontrol-db-core"))
    dokka(project(":kontrol-db-hsqldb"))
    dokka(project(":kontrol-db-sqlserver"))
    dokka(project(":kontrol-db-oracle"))
    dokka(project(":kontrol-db-postgres"))
}

reporting {
    reports {
        val integrationTestCodeCoverageReport by creating(JacocoCoverageReport::class) {
            this.testSuiteName = "integrationTest"
        }
    }
}

val integrationTestCodeCoverageReport =
    tasks.named<JacocoReport>("integrationTestCodeCoverageReport") {
        classDirectories.setFrom(
            classDirectories.files.map {
                fileTree(it).matching {
                    exclude("**/generated/**")
                }
            },
        )
    }

val integrationTestCoverageLimit: String by project

val jacocoIntegrationTestCoverageVerification by tasks.registering(JacocoCoverageVerification::class) {
    group = "verification"
    doFirst {
        println(
            "file:///" +
                integrationTestCodeCoverageReport
                    .get()
                    .reports.html.outputLocation
                    .get()
                    .asFile
                    .toURI()
                    .path + "index.html",
        )
    }
    violationRules {
        rule {
            limit {
                minimum = integrationTestCoverageLimit.toBigDecimal().divide(BigDecimal.valueOf(100)).setScale(2)
            }
        }
    }
    mustRunAfter(integrationTestCodeCoverageReport)
}

tasks.check {
    dependsOn(integrationTestCodeCoverageReport, jacocoIntegrationTestCoverageVerification)
}

tasks.assemble {
    dependsOn(tasks.named("dokkaGenerateHtml"))
}

tasks.wrapper {
    gradleVersion = libs.versions.gradle.get()
}

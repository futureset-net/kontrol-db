@file:Suppress("UnstableApiUsage")

import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URI

plugins {
    kotlin("jvm").apply(false)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dokka)
    id("jacoco-report-aggregation")
}

println("VERSION is $version")

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
        target("*/src/*/kotlin/**/*.kt", "*.gradle.kts", "*/*.kts", "buildSrc/src/**/*.kts")
    }
}

val referenceToLibs = libs
allprojects {
//    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
//        configure<KotlinJvmPlugin> {
//            jvmToolchain {
//                languageVersion.set(referenceToLibs.versions.java.map(JavaLanguageVersion::of))
//            }
//        }
//    }
    pluginManager.withPlugin("java") {
        configure<JavaPluginExtension> {
            toolchain {
                languageVersion = JavaLanguageVersion.of(referenceToLibs.versions.java.get())
            }
        }
    }
}
subprojects {
    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            failOnWarning.set(true)
            samples = files(rootDir.resolve("integrationTest/src/main/kotlin/net/futureset/kontroldb/samples"))
            includes.from(
                *project.files("extra.md").filter {
                    it.exists()
                }.files.toTypedArray(),
            )
            sourceLink {
                localDirectory.set(projectDir.resolve("src"))
                remoteUrl.set(URI.create("https://github.com/futureset-net/kontrol-db/tree/main").toURL())
                remoteLineSuffix.set("#L")
            }
            perPackageOption {
                matchingRegex.set(".*(modelchange|dialect|dsl).*")
                suppressObviousFunctions.set(true)
                documentedVisibilities.set(setOf(DokkaConfiguration.Visibility.PUBLIC))
                reportUndocumented.set(false)
                skipEmptyPackages.set(true)
                skipDeprecated.set(false)
            }
            perPackageOption {
                matchingRegex.set(".*")
                suppress.set(true)
            }
        }
    }
}

tasks.dokkaJekyllMultiModule.configure {
    moduleName.set(project.name)
    includes.from(file("extra.md"))
    this.outputDirectory.set(project.layout.buildDirectory.dir("docs"))
}

tasks.assemble {
    dependsOn(tasks.dokkaJekyllMultiModule)
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
                testType = TestSuiteType.UNIT_TEST
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

    tasks.withType<Test>() {
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

val databasesubprojects = listOf(project(":kontrol-db-hsqldb"), project(":kontrol-db-sqlserver"), project(":kontrol-db-postgres"))

dependencies {
    databasesubprojects.forEach { jacocoAggregation(it) }
    jacocoAggregation(project(":kontrol-db-core"))
}
reporting {
    reports {
        val integrationTestCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testType = TestSuiteType.INTEGRATION_TEST
        }
    }
}

val integrationTestCodeCoverageReport = tasks.named<JacocoReport>("integrationTestCodeCoverageReport") {
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
        println("file://" + integrationTestCodeCoverageReport.get().reports.html.outputLocation.get().asFile.toURI().path + "index.html")
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

tasks.wrapper {
    gradleVersion = libs.versions.gradle.get()
}

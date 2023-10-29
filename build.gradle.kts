import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    alias(libs.plugins.spotless)
    id("jacoco-report-aggregation")
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

    sourceSets {
        getByName("main") {
            kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
        }
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

    tasks.withType<Test>() {
        testLogging {
            events("passed", "skipped", "failed")
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

val databasesubprojects = listOf(project(":hsqldb"), project(":sqlserver"))
dependencies {
    databasesubprojects.forEach(::jacocoAggregation)
}

reporting {
    reports {
        val integrationTestCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testType = TestSuiteType.INTEGRATION_TEST
        }
    }
}

val integrationTestCodeCoverageReport = tasks.named<JacocoReport>("integrationTestCodeCoverageReport")
val integrationTestCoverageLimit: String by project

val jacocoIntegrationTestCoverageVerification by tasks.registering(JacocoCoverageVerification::class) {
    group = "verification"
    executionData(*databasesubprojects.map { it.tasks.named("integrationTest").get() }.toTypedArray())
    sourceSets(*(databasesubprojects.map { it.sourceSets["main"] } + project(":core").sourceSets["main"]).toTypedArray())
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

val verGradle: String by project

tasks.wrapper {
    gradleVersion = verGradle
}

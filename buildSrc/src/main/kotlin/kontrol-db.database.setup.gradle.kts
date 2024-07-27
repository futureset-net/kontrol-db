plugins {
    kotlin("jvm")
    id("jacoco")
    id("kontrol-db-koin-codegen")
    id("kontrol-db-maven-publish")
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

extensions.configure<TestingExtension> {
    suites {
        register<JvmTestSuite>("integrationTest") {
            sources {
                kotlin {
                    srcDir(project.layout.buildDirectory.dir("generated/integrationTest/kotlin"))
                }
            }
            useJUnitJupiter(versionCatalog.findVersion("junit").get().requiredVersion)
            testType = TestSuiteType.INTEGRATION_TEST
            dependencies {
                versionCatalog.findLibrary("assertj").ifPresent {
                    implementation(it)
                }
                implementation(project(":integrationTest"))
                implementation(project(":kontrol-db-core"))
                implementation(project())
            }
        }
    }
}

extensions.configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_20
    targetCompatibility = JavaVersion.VERSION_20
    withSourcesJar()
}

val jacocoIntegrationTestReport by tasks.registering(JacocoReport::class) {
    group = "verification"
    this.sourceSets(project.sourceSets["main"], project(":kontrol-db-core").sourceSets["main"])
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

tasks.check {
    dependsOn("integrationTest")
}

dependencies {
    api(project(":kontrol-db-core"))
}

rootProject.dependencies {
    add("jacocoAggregation", project)
}

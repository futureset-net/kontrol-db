import java.net.URI

plugins {
    application
    `maven-publish`
    alias(libs.plugins.ksp)
}

description = "core engine and default templates for kontrol-db"

dependencies {
    ksp(libs.koin.compiler)

    api(libs.bundles.koin)
    api(libs.kotlin.reflect)
    api(libs.logback.classic)
    api(libs.bundles.jackson.yaml)
}

val unitTestCoverageLimit: String by project

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

tasks.named<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    executionData(tasks.test.get())
    dependsOn(tasks.test)
    violationRules {
        rule {
            limit {
                minimum = unitTestCoverageLimit.toBigDecimal().divide(BigDecimal.valueOf(100)).setScale(2)
            }
        }
    }
}

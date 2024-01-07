plugins {
    application
    id("kontrol-db-maven-publish")
    id("kontrol-db-koin-codegen")
    id("org.jetbrains.dokka")
}

description = "core engine and default templates for kontrol-db"

dependencies {

    api(libs.bundles.koin)
    api(libs.kotlin.reflect)
    api(libs.logback.classic)
    api(libs.bundles.jackson.yaml)
}

val unitTestCoverageLimit: String by project

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

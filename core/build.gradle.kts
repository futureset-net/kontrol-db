plugins {
    application
    alias(libs.plugins.ksp)
}
dependencies {
    ksp(libs.koin.compiler)

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

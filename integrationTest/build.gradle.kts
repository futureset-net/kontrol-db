plugins {
    alias(libs.plugins.ksp)
}

dependencies {

    implementation(project(":core"))
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.assertj)
    ksp(libs.koin.compiler)
}

tasks.test {
    finalizedBy("jacocoTestReport")
    enabled = false
}

// tasks.jacocoTestReport {
//    sourceSets(project(":core").sourceSets.getByName("main"))
// }
//
// tasks.jacocoTestCoverageVerification {
//    sourceSets(project(":core").sourceSets.getByName("main"))
//    mustRunAfter("jacocoTestReport")
// }

// val integrationTestCoverageLimit: String by project
//

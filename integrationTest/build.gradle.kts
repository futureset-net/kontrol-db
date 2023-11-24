plugins {
    alias(libs.plugins.ksp)
}

dependencies {

    implementation(project(":kontrol-db-core"))
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.assertj)
    ksp(libs.koin.compiler)
}

tasks.test {
    finalizedBy("jacocoTestReport")
    enabled = false
}

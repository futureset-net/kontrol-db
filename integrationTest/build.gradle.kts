plugins {
    id("kontrol-db-koin-codegen")
}

dependencies {

    implementation(project(":kontrol-db-core"))
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.bundles.junit5)
    testImplementation(libs.assertj)
}

tasks.test {
    finalizedBy("jacocoTestReport")

    enabled = false
}

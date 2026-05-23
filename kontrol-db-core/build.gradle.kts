plugins {
    application
    id("kontrol-db-maven-publish")
    id("kontrol-db-koin-codegen")
    `dokka-convention`
}
description = "core engine and default templates for kontrol-db"

dependencies {

    api(platform(libs.koin.bom))
    api(libs.bundles.koin)
    api(libs.kotlin.reflect)
    api(libs.logback.classic)
    api(libs.bundles.jackson.yaml)
}

val jacocoExclude = listOf("**/generated/**")

tasks.named<JacocoReport>("jacocoTestReport") {
    classDirectories.setFrom(
        classDirectories.files.map {
            fileTree(it).matching {
                exclude(jacocoExclude)
            }
        },
    )
}

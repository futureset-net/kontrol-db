plugins {
    `kotlin-dsl`
    jacoco
}

dependencies {
    api(libs.kotlin.jvm.plugin)
    api(libs.ksp.plugin)
    api(libs.docker.remote.plugin)
    implementation(libs.dokka.gradle.plugin)
}

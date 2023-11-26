plugins {
    `kotlin-dsl`
    jacoco
}

dependencies {
    api(libs.kotlin.jvm.plugin)
    api(libs.ksp.plugin)
}

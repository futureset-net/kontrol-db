plugins {
    alias(libs.plugins.ksp)
}

sourceSets {
    getByName("main") {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

dependencies {
    ksp(libs.koin.compiler)
    implementation(project(":core"))
    api(libs.sqlserver)
}

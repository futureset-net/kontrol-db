plugins {
    alias(libs.plugins.ksp)
}

sourceSets {
    getByName("main") {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
    }
}

dependencies {
    implementation(project(":core"))
    ksp(libs.koin.compiler)
    api(libs.hsqldb)
    api(libs.hsqldb.tool)
}

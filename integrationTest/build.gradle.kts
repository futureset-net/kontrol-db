plugins {
    alias(libs.plugins.ksp)
}

sourceSets {
    getByName("main") {
        kotlin.srcDir("$buildDir/generated/ksp/main/kotlin")
    }
}
dependencies {

    implementation(project(":core"))
    api(libs.hsqldb)
    testImplementation(libs.bundles.spek)
    ksp(libs.koin.compiler)
}

tasks.jacocoTestReport {
    sourceSets(project(":core").sourceSets.getByName("main"))
}

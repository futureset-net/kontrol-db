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
    api(libs.hsqldb)
    testImplementation(libs.bundles.junit5)
    ksp(libs.koin.compiler)
}

tasks.test {
    finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
    additionalSourceDirs(project(":core").sourceSets.getByName("main").kotlin.sourceDirectories)
    additionalClassDirs(project(":core").sourceSets.getByName("main").output.classesDirs)
}

tasks.jacocoTestCoverageVerification {
    additionalSourceDirs(project(":core").sourceSets.getByName("main").kotlin.sourceDirectories)
    additionalClassDirs(project(":core").sourceSets.getByName("main").output.classesDirs)

    mustRunAfter("jacocoTestReport")
}

val integrationTestCoverageLimit: String by project

tasks.named<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    executionData(tasks.test.get())
    dependsOn(tasks.test)
    violationRules {
        rule {
            limit {
                minimum = integrationTestCoverageLimit.toBigDecimal().divide(BigDecimal.valueOf(100)).setScale(2)
            }
        }
    }
}

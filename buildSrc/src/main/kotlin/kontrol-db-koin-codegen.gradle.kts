plugins {
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    add("ksp", versionCatalog.findLibrary("koin.compiler").get())
}

ksp {
    arg("KOIN_DEFAULT_MODULE", "true")
}

extensions.configure<JavaPluginExtension> {
    toolchain {
        languageVersion = JavaLanguageVersion.of(versionCatalog.findVersion("java").get().requiredVersion)
    }
    sourceSets {
        getByName("main") {
            kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/main/kotlin"))
        }
    }
}

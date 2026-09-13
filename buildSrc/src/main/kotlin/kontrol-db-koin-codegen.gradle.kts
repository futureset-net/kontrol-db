plugins {
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val koinCompiler = versionCatalog.findLibrary("koin.compiler").get().get()
listOf("ksp", "kspTest", "kspIntegrationTest").forEach { configurationName ->
    configurations.maybeCreate(configurationName).dependencies.add(project.dependencies.create(koinCompiler))
}

ksp {
    arg("KOIN_DEFAULT_MODULE", "true")
}

extensions.configure<JavaPluginExtension> {
    toolchain {
        languageVersion = JavaLanguageVersion.of(versionCatalog.findVersion("java").get().requiredVersion)
    }
    sourceSets.configureEach {
        kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/$name/kotlin"))
    }
}

extensions.configure<TestingExtension> {
    suites.withType<JvmTestSuite>().configureEach {
        sources {
            kotlin.srcDir(project.layout.buildDirectory.dir("generated/ksp/$name/kotlin"))
        }
    }
}


import org.jetbrains.dokka.gradle.DokkaExtension
import org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier

if (!project.plugins.hasPlugin("org.jetbrains.dokka")) {
    project.pluginManager.apply("org.jetbrains.dokka")
}

configure<DokkaExtension> {
    dokkaPublications.named("html") {
        suppressInheritedMembers.set(true)
        suppressObviousFunctions.set(true)
        failOnWarning.set(true)
    }
    dokkaSourceSets.configureEach {
        samples.from(files(rootDir.resolve("integrationTest/src/main/kotlin/net/futureset/kontroldb/samples")))
        sourceLink {
            // Read docs for more details: https://kotlinlang.org/docs/dokka-gradle.html#source-link-configuration
            remoteUrl("https://github.com/futureset-net/kontrol-db/tree/main")
            localDirectory.set(rootDir)
            remoteLineSuffix.set("#L")
        }
        perPackageOption {
            matchingRegex.set(".*(modelchange|dialect|dsl).*")
            documentedVisibilities.set(setOf(VisibilityModifier.Public))
            reportUndocumented.set(false)
            skipEmptyPackages.set(true)
            skipDeprecated.set(false)
        }
        perPackageOption {
            matchingRegex.set(".*")
            suppress.set(true)
        }
        includes.from("extra.md")
    }
}

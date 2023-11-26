import java.net.URI

plugins {
    `maven-publish`
}

extensions.configure<PublishingExtension> {
    publications {
        create<MavenPublication>("mavenPublication") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI.create("https://maven.pkg.github.com/futureset/kontrol-db")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}

tasks.withType<PublishToMavenRepository>().configureEach {
    dependsOn("assemble")
}

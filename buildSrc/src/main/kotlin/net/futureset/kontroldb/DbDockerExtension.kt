package net.futureset.kontroldb

import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

interface DbDockerExtension {

    val imageId: Property<String>
    val dockerEnabled: Property<Boolean>
    val containerName: Property<String>
    val envProperties: MapProperty<String, String>
    val internalToExternalPortMap: MapProperty<Int, Int>
    val waitForStartupLogMessage: Property<String>
}

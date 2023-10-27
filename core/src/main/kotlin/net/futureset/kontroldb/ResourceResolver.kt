package net.futureset.kontroldb

import java.io.BufferedReader
import java.nio.file.Path
import kotlin.io.path.inputStream

class ResourceResolver(
    private val externalPath: Path,
    private val classLoader: ClassLoader = Thread.currentThread().contextClassLoader,
) : AutoCloseable {

    private val checksumCache = mutableMapOf<String, Int>()

    fun resourceHash(resource: Resource): Int {
        return checksumCache.computeIfAbsent(resource.path) {
            inputStream(resource)
                .buffered()
                .use {
                    var c: Int
                    var result = 0
                    while (true) {
                        c = it.read()
                        if (c == -1) break
                        result = 31 * result + c
                    }
                    result
                }
        }
    }

    fun resourceText(resource: Resource): String {
        return inputStream(resource).bufferedReader().readText()
    }

    private fun inputStream(resource: Resource) =
        (classLoader.getResourceAsStream(resource.path) ?: externalPath.resolve(resource.path).inputStream())

    fun Resource.checksum(): Int {
        return resourceHash(this)
    }

    fun Resource.text(): String {
        return resourceText(this)
    }
    override fun close() {
        checksumCache.clear()
    }

    fun reader(resource: Resource): BufferedReader {
        return inputStream(resource).bufferedReader()
    }
}

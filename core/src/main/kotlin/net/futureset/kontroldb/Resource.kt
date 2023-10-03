package net.futureset.kontroldb

import java.io.BufferedReader
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Objects
import kotlin.io.path.inputStream

data class Resource(val path: String) {

    val lazyHashCode: Int by lazy {
        inputStream()?.use {
            var c: Int
            var result = 0
            while (true) {
                c = it.read()
                if (c == -1) break
                result = 31 * result + c
            }
            result
        } ?: 0
    }

    companion object {
        fun resource(input: String) =
            Resource(
                input.replace("\\", "/"),
            )
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Resource) {
            Objects.equals(this.path, other.path) && Objects.equals(this.lazyHashCode, other.lazyHashCode)
        } else {
            false
        }
    }

    override fun hashCode() = Objects.hash(this.path, lazyHashCode)

    private fun inputStream(): InputStream? {
        return Paths.get(this.path).takeIf(Files::exists)?.inputStream()
            ?: this::class.java.getResource("/" + path.trimStart('/'))
                ?.openStream()
    }

    fun reader(): BufferedReader {
        return requireNotNull(inputStream()).bufferedReader()
    }

    fun text(): String {
        return reader().readText()
    }
}

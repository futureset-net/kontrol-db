package net.futureset.kontroldb

import java.util.Objects

data class Resource(val path: String) {

    val lazyHashCode: Int by lazy {
        text().hashCode()
    }
    companion object {
        fun resource(input: String) =
            Resource(
                input.trim('/', '\\')
                    .split(Regex("/")).joinToString(separator = "/"),
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

    fun text(): String {
        return requireNotNull(this::class.java.getResource("/$path")?.readText())
    }
}

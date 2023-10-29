package net.futureset.kontroldb

import com.fasterxml.jackson.annotation.JsonValue
import java.nio.file.Paths

data class Resource(@JsonValue val path: String) {

    companion object {
        fun resource(path: String): Resource {
            val cleanedPath = path.replace("\\", "/").trimEnd('/')
            require(!Paths.get(path).isAbsolute && !cleanedPath.startsWith("/")) {
                "Path $path must be relative"
            }
            return Resource(cleanedPath)
        }
    }
}

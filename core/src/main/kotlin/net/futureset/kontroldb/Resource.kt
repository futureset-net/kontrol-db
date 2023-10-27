package net.futureset.kontroldb

import com.fasterxml.jackson.annotation.JsonValue
import java.nio.file.Path
import java.nio.file.Paths

data class Resource(@JsonValue val path: String) {

    companion object {
        fun resource(path: Path): Resource {
            require(!path.isAbsolute) { "Path $path must be relative" }
            return Resource(path.toString().replace("\\", "/"))
        }

        fun resource(path: String): Resource {
            return resource(Paths.get(path))
        }
    }
}

package net.futureset.kontroldb.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ext.NioPathDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import net.futureset.kontroldb.KontrolDbConfig
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

class ConfigFileControl {

    val mapper = ObjectMapper(YAMLFactory().enable(YAMLGenerator.Feature.MINIMIZE_QUOTES))
        .enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
        .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .registerModule(
            SimpleModule("pathToString").addSerializer(Path::class.java, ToStringSerializer())
                .addDeserializer(Path::class.java, NioPathDeserializer()),
        )
        .registerKotlinModule()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    fun configFile(file: Path, currentConfig: KontrolDbConfig): KontrolDbConfig {
        val inputStream: InputStream =
            if (Files.exists(file)) {
                Files.newInputStream(file)
            } else {
                requireNotNull(javaClass.getResourceAsStream("/" + file.toString().replace("\\", "/"))) {
                    "Could not locate $file by file or classpath"
                }
            }
        inputStream.use {
            return mapper.readerForUpdating(currentConfig).readValue(it, KontrolDbConfig::class.java)
        }
    }

    inline fun <reified T> merge(first: T, second: T?): T {
        return if (second == null) {
            first
        } else {
            val a = mapper.valueToTree<JsonNode>(first)
            val b = mapper.valueToTree<JsonNode>(second)
            val x = mapper.readerForUpdating(a).readValue<JsonNode>(b)
            return mapper.convertValue(x, T::class.java)
        }
    }
}

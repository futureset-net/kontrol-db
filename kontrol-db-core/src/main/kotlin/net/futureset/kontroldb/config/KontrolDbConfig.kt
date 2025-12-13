package net.futureset.kontroldb.config

import com.fasterxml.jackson.annotation.JsonMerge
import net.futureset.kontroldb.settings.ExecutionSettings
import net.futureset.kontroldb.settings.TargetSettings
import kotlin.reflect.full.createInstance
import org.koin.core.module.Module as KontrolModule

data class KontrolDbConfig(
    val dialect: String?,
    val modules: List<String>?,
    @JsonMerge
    val targetSettings: TargetSettings?,
    @JsonMerge
    val executionSettings: ExecutionSettings?,
) {
    fun resolvedModules(): List<KontrolModule> = modules?.map(this::classNameToModule).orEmpty()

    private fun classNameToModule(className: String): KontrolModule {
        val clazz = Class.forName(className)
        if (KontrolModule::class.java.isAssignableFrom(clazz)) {
            return clazz.kotlin.createInstance() as KontrolModule
        }
        val generatedClass =
            "org.koin.ksp.generated." + clazz.simpleName + "Gen" + (
                clazz.`package`.name
                    .lowercase()
                    .replace(".", "_")
                ) + "Kt"
        return Class
            .forName(generatedClass)
            .getMethod("getModule", clazz)
            .invoke(null, clazz.kotlin.createInstance()) as KontrolModule
    }
}

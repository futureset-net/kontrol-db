package net.futureset.kontroldb.generator

import net.futureset.kontroldb.model.Resource
import net.futureset.kontroldb.model.SqlString
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.settings.EffectiveSettings
import java.io.BufferedReader
import kotlin.reflect.KClass

abstract class DbAwareGenerator<T : ModelChange>(override val es: EffectiveSettings, override val type: KClass<T>) : SqlGenerator<T> {

    override val priority: GeneratorPriority = GeneratorPriority.DATABASE

    fun SqlString?.toQuoted(block: (String) -> String = { it }): String {
        return this?.toQuoted(es)?.takeIf { it.isNotBlank() }?.let(block) ?: ""
    }

    fun <T : SqlString> joinQuotableValues(
        items: Collection<T>,
        separateBy: String = ", ",
        block: (String) -> String = { it },
    ): String {
        return items.joinToString(separator = separateBy, transform = { block(it.toQuoted()) })
    }

    protected open fun convertSingle(): T.() -> String? = { null }

    override fun convert(change: T): List<String> {
        return listOfNotNull(convertSingle().invoke(change))
    }

    final override fun canApply(): Boolean {
        return canApplyTo(es)
    }

    protected fun Resource.text() = es.resourceResolver.resourceText(this)

    protected fun Resource.reader(): BufferedReader {
        return es.resourceResolver.reader(this)
    }

    open fun canApplyTo(es: EffectiveSettings): Boolean {
        return true
    }
}

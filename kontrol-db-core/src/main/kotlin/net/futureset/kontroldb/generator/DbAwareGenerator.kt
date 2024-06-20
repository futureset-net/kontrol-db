package net.futureset.kontroldb.generator

import net.futureset.kontroldb.model.Resource
import net.futureset.kontroldb.model.SqlString
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.settings.EffectiveSettings
import java.io.BufferedReader

abstract class DbAwareGenerator<T : ModelChange>(
    override val effectiveSettings: EffectiveSettings,
    override val priority: GeneratorPriority = GeneratorPriority.CUSTOM,
) : SqlGenerator<T> {

    override lateinit var sqlGeneratorResolver: SqlGeneratorResolver
    fun SqlString?.toQuoted(block: (String) -> String = { it }): String {
        return this?.toQuoted(effectiveSettings)?.takeIf { it.isNotBlank() }?.let(block) ?: ""
    }

    fun <T : SqlString> joinQuotableValues(
        items: Collection<T>,
        separateBy: String = ", ",
        block: (String) -> String = { it },
    ): String {
        return items.joinToString(separator = separateBy, transform = { block(it.toQuoted()) })
    }

    fun generateSqlSingle(change: ModelChange): String {
        val result = sqlGeneratorResolver.resolveGenerator(change)?.convert(change) ?: emptyList()
        require(result.size < 2)
        return result.firstOrNull() ?: ""
    }

    fun generateSql(change: ModelChange): List<String> {
        return sqlGeneratorResolver.resolveGenerator(change)?.convert(change)?.filterNotNull() ?: emptyList()
    }

    open fun convertSingle(): T.() -> String? = { null }

    override fun convert(change: T): List<String> {
        return listOfNotNull(convertSingle().invoke(change))
    }

    final override fun canApply(): Boolean {
        return canApplyTo(effectiveSettings)
    }

    fun Resource.text() = effectiveSettings.resourceResolver.resourceText(this)

    fun Resource.reader(): BufferedReader {
        return effectiveSettings.resourceResolver.reader(this)
    }

    open fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return true
    }
}

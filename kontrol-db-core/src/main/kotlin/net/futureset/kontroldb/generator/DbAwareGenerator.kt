package net.futureset.kontroldb.generator

import net.futureset.kontroldb.model.Resource
import net.futureset.kontroldb.model.SqlString
import net.futureset.kontroldb.modelchange.ModelChange
import net.futureset.kontroldb.settings.EffectiveSettings
import java.io.BufferedReader
import kotlin.reflect.KClass

abstract class DbAwareGenerator<T : ModelChange>(
    override val es: EffectiveSettings,
) : SqlGenerator<T> {

    override val priority: GeneratorPriority = GeneratorPriority.DATABASE

    override lateinit var sqlGeneratorResolver: SqlGeneratorResolver
    fun SqlString?.toQuoted(block: (String) -> String = { it }): String {
        return this?.toQuoted(es)?.takeIf { it.isNotBlank() }?.let(block) ?: ""
    }

    override fun type(): KClass<T> {
        return this::class.constructors.first().parameters.first().type.classifier as KClass<T>
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
        return canApplyTo(es)
    }

    fun Resource.text() = es.resourceResolver.resourceText(this)

    fun Resource.reader(): BufferedReader {
        return es.resourceResolver.reader(this)
    }

    open fun canApplyTo(es: EffectiveSettings): Boolean {
        return true
    }
}

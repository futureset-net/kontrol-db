package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

abstract class DbAwareTemplate<T : ModelChange>(
    private val effectiveSettings: EffectiveSettings,
    override val priority: TemplatePriority = TemplatePriority.CUSTOM,
) : SqlTemplate<T> {

    override lateinit var templateResolver: TemplateResolver
    fun SqlString?.toSql(block: (String) -> String = { it }): String {
        return this?.toSql(effectiveSettings)?.takeIf { it.isNotBlank() }?.let(block) ?: ""
    }

    fun <T : SqlString> forEach(
        items: Collection<T>,
        separateBy: String = ", ",
        block: (T) -> String = { sqlString: SqlString ->
            sqlString.toSql()
        },
    ): String {
        return items.joinToString(separator = separateBy, transform = block)
    }

    fun otherTemplate(change: ModelChange): String {
        var result = templateResolver.findTemplate(change)?.convert(change) ?: emptyList()
        require(result.size < 2)
        return result.firstOrNull() ?: ""
    }
    fun otherTemplateOutput(change: ModelChange): List<String> {
        return templateResolver.findTemplate(change)?.convert(change)?.filterNotNull() ?: emptyList()
    }
    open fun convertToSingleStatement(change: T): String? = null

    override fun convert(change: T): List<String> {
        return listOfNotNull(convertToSingleStatement(change))
    }

    final override fun canApply(): Boolean {
        return canApplyTo(effectiveSettings)
    }

    open fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return true
    }
}

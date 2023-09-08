package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

abstract class DbAwareTemplate<T : ModelChange>(
    private val effectiveSettings: EffectiveSettings,
    override val priority: TemplatePriority = TemplatePriority.CUSTOM,
) :
    SqlTemplate<T> {

    fun SqlString?.toSql(): String {
        return this?.toSql(effectiveSettings) ?: ""
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

    final override fun canApply(): Boolean {
        return canApplyTo(effectiveSettings)
    }

    open fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return true
    }
}

package net.futureset.kontroldb.model

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.settings.EffectiveSettings

data class ColumnDefinition(
    val columnName: DbIdentifier,
    val columnType: ColumnType,
    val nullable: Boolean,
) : SqlString {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(
            columnName.toQuoted(effectiveSettings),
            columnType.toQuoted(effectiveSettings),
            when (nullable) {
                effectiveSettings.nullableByDefault -> null
                true -> "NULL"
                false -> "NOT NULL"
            },
        ).joinToString(separator = " ")
    }

    @KontrolDbDslMarker
    data class ColumnDefinitionBuilder(
        private val name: String,
        private val type: ColumnType,
        private var nullable: Boolean = false,
    ) : Builder<ColumnDefinitionBuilder, ColumnDefinition> {

        fun notNull() = apply {
            nullable = false
        }

        fun nullable() = apply {
            nullable = true
        }

        override fun build(): ColumnDefinition {
            return ColumnDefinition(DbIdentifier(name), type, nullable)
        }
    }
}

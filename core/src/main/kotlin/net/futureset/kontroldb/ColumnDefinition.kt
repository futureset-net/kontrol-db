package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

data class ColumnDefinition(
    val columnName: DbIdentifier,
    val dbColumnType: DbColumnType,
    val nullable: Boolean = false,
) : SqlString {
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(
            columnName.toSql(effectiveSettings),
            dbColumnType.toSql(effectiveSettings),
            when (nullable) {
                effectiveSettings.nullableByDefault -> null
                true -> "NULL"
                false -> "NOT NULL"
            },
        ).joinToString(separator = " ")
    }
    data class ColumnDefinitionBuilder(
        val name: String,
        private val type: DbColumnType,
        private var nullable: Boolean = false,
    ) : Builder<ColumnDefinition> {

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

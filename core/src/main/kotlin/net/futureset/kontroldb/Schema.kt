package net.futureset.kontroldb

import net.futureset.kontroldb.settings.EffectiveSettings

interface Schema : SqlString {
    val schemaName: DbIdentifier
}

class CatalogAndSchema(val catalogName: DbIdentifier?, override val schemaName: DbIdentifier) : Schema {
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(
            catalogName?.takeIf { effectiveSettings.isOutputCatalog } ?: effectiveSettings.defaultCatalog,
            schemaName.takeIf { effectiveSettings.isOutputSchema || it != effectiveSettings.defaultSchema } ?: effectiveSettings.defaultSchema,
        )
            .joinToString(separator = ".") { di -> di.toSql(effectiveSettings) }
    }
}

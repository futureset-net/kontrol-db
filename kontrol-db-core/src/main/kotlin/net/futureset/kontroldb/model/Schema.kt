package net.futureset.kontroldb.model

import net.futureset.kontroldb.settings.EffectiveSettings

interface Schema : SqlString {
    val schemaName: DbIdentifier
}

class CatalogAndSchema(
    val catalogName: DbIdentifier?,
    override val schemaName: DbIdentifier,
) : Schema {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String = listOfNotNull(
        catalogName?.takeIf { effectiveSettings.isOutputCatalog } ?: effectiveSettings.defaultCatalog,
        schemaName.takeIf { effectiveSettings.isOutputSchema || it != effectiveSettings.defaultSchema }
            ?: effectiveSettings.defaultSchema,
    ).joinToString(separator = ".") { di -> di.toQuoted(effectiveSettings) }
}

package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings

interface Schema : SqlString {
    val schemaName: DbIdentifier
}

class CatalogAndSchema(val catalogName: DbIdentifier?, override val schemaName: DbIdentifier) : Schema {
    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(
            catalogName?.takeIf { effectiveSettings.outputCatalog } ?: effectiveSettings.defaultCatalog,
            schemaName?.takeIf { effectiveSettings.outputSchema || it != effectiveSettings.defaultSchema } ?: effectiveSettings.defaultSchema,
        )
            .joinToString(separator = ".") { di -> di.toSql(effectiveSettings) }
    }
}

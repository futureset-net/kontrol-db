package net.futureset.kmigrate

import net.futureset.kmigrate.settings.EffectiveSettings

data class SchemaObject(val name: DbIdentifier, val schema: Schema? = null) : SqlString {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(schema ?: effectiveSettings.defaultSchema?.takeIf { effectiveSettings.outputSchema }, name).joinToString(separator = ".") { it.toSql(effectiveSettings) }
    }
}

@KMigrateDslMarker
class SchemaObjectBuilder : Builder<SchemaObject> {
    private var name: String? = null
    private var schema: String? = null
    private var catalog: String? = null

    fun name(name: String): SchemaObjectBuilder = apply {
        this.name = name
    }

    fun schema(schema: String?): SchemaObjectBuilder = apply {
        this.schema = schema
    }

    fun catalog(catalog: String?): SchemaObjectBuilder = apply {
        this.catalog = catalog
    }

    override fun build(): SchemaObject {
        return SchemaObject(
            name = DbIdentifier(requireNotNull(name)),
            schema = schema?.let {
                CatalogAndSchema(
                    schemaName = DbIdentifier(it),
                    catalogName = catalog?.let(::DbIdentifier),
                )
            },
        )
    }
}

package net.futureset.kontroldb

import net.futureset.kontroldb.modelchange.TableAlias
import net.futureset.kontroldb.settings.EffectiveSettings

data class SchemaObject(val name: DbIdentifier, val schema: Schema? = null) : SqlString {

    override fun toSql(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(schema, name).joinToString(separator = ".") { it.toSql(effectiveSettings) }
    }

    fun alias(label: String? = null): TableAlias {
        return TableAlias(label, this)
    }
}

@KontrolDbDslMarker
data class SchemaObjectBuilder(
    private var schemaObject: SchemaObject = SchemaObject(name = DbIdentifier("not-specified")),
) : Builder<SchemaObjectBuilder, SchemaObject> {

    fun name(name: String): SchemaObjectBuilder = apply {
        this.schemaObject = schemaObject.copy(name = DbIdentifier(name))
    }

    fun schema(schema: String): SchemaObjectBuilder = apply {
        schemaObject = schemaObject.copy(schema = CatalogAndSchema(schemaName = DbIdentifier(schema), catalogName = null))
    }

    fun catalogAndSchema(catalog: String, schema: String): SchemaObjectBuilder = apply {
        schemaObject = schemaObject.copy(schema = CatalogAndSchema(schemaName = DbIdentifier(schema), catalogName = DbIdentifier(catalog)))
    }

    override fun build() = schemaObject
}

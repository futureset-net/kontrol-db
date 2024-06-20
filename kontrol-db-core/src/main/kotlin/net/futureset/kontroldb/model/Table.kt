package net.futureset.kontroldb.model

import net.futureset.kontroldb.modelchange.TableAlias
import net.futureset.kontroldb.modelchange.TablePersistence
import net.futureset.kontroldb.settings.EffectiveSettings

data class Table(val schemaObject: SchemaObject, val tablePersistence: TablePersistence = TablePersistence.NORMAL) :
    SqlString {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return listOfNotNull(schemaObject.schema, effectiveSettings.tempTable(this).schemaObject.name).joinToString(separator = ".") { it.toQuoted(effectiveSettings) }
    }

    fun alias(label: String? = null): TableAlias {
        return TableAlias(label, this)
    }
}

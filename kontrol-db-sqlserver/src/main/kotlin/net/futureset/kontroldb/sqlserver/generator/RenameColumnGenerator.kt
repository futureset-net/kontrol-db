package net.futureset.kontroldb.sqlserver.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.RenameColumn
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class RenameColumnGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<RenameColumn>(es, RenameColumn::class) {
    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "sqlserver"

    override fun convertSingle(): RenameColumn.() -> String? = {
        // Build the 'schema.table.column' identifier for sp_rename
        val schemaPrefix = table.schema?.toQuoted(es)?.let { "$it." } ?: ""
        val target = "${schemaPrefix}${table.name.toQuoted(es)}.${oldName.toQuoted(es)}"
        "EXEC sp_rename '$target', ${newName.toQuoted(es)}, 'COLUMN'"
    }
}

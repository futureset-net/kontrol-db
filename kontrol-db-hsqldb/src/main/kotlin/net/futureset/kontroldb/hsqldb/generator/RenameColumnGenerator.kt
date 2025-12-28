package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.RenameColumn
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class RenameColumnGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<RenameColumn>(es, RenameColumn::class) {
    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "hsqldb"

    override fun convertSingle(): RenameColumn.() -> String? = {
        table.toQuoted { "ALTER TABLE $it ALTER COLUMN ${oldName.toQuoted(es)} RENAME TO ${newName.toQuoted(es)}" }
    }
}

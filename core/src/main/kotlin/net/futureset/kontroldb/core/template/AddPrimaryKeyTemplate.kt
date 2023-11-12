package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class AddPrimaryKeyTemplate(db: EffectiveSettings) : DbAwareTemplate<AddPrimaryKey>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<AddPrimaryKey> {
        return AddPrimaryKey::class
    }

    override fun convertToSingleStatement(change: AddPrimaryKey): String {
        return change.table.takeUnless { change.inline }.toSql { "ALTER TABLE $it ADD" } +
            change.constraintName.toSql { " CONSTRAINT $it" } +
            " PRIMARY KEY(" + forEach(change.columnReferences) + ")"
    }
}
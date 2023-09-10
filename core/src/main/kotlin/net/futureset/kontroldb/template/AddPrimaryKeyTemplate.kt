package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class AddPrimaryKeyTemplate(db: EffectiveSettings) : DbAwareTemplate<AddPrimaryKey>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<AddPrimaryKey> {
        return AddPrimaryKey::class
    }

    override fun convertToSingleStatement(change: AddPrimaryKey): String? {
        return """
${change.table.toSql{"ALTER TABLE $it ADD"}}${change.constraintName.toSql{" CONSTRAINT $it"}} PRIMARY KEY(${forEach(change.columnReferences)})
        """.trimIndent()
    }
}

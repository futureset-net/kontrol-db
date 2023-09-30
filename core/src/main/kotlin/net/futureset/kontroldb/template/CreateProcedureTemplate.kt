package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.CreateProcedure
import net.futureset.kontroldb.settings.EffectiveSettings

class CreateProcedureTemplate(db: EffectiveSettings) :
    DbAwareTemplate<CreateProcedure>(db, TemplatePriority.DEFAULT) {
    override fun type() = CreateProcedure::class

    override fun convertToSingleStatement(change: CreateProcedure): String? {
        val body = change.body ?: change.path?.text()
        return if (change.wholeDefinition) body else "CREATE PROCEDURE ${change.procedure.toSql()}\n$body"
    }
}

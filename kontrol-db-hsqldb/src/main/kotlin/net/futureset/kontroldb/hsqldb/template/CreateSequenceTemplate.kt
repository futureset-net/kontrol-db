package net.futureset.kontroldb.hsqldb.template

import net.futureset.kontroldb.modelchange.CreateSequence
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import net.futureset.kontroldb.template.trimBlankLines
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class CreateSequenceTemplate(db: EffectiveSettings) : DbAwareTemplate<CreateSequence>(db, TemplatePriority.DATABASE) {
    override fun type() = CreateSequence::class

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.databaseName == "hsqldb"
    }

    override fun convertToSingleStatement(change: CreateSequence): String =
        change.run {
            """CREATE SEQUENCE ${schemaObject.toSql()}
                ${columnType?.let { "AS ${it.toSql()}" }.orEmpty()}
                START WITH $startWith
                ${incrementBy.takeUnless { it == 1L }?.let { "INCREMENT BY $it" }.orEmpty()}
                ${minValue?.let { "MINVALUE $it" }.orEmpty()}
                ${maxValue?.let { "MAXVALUE $it" }.orEmpty()}
                ${cycle?.let { if (it) "CYCLE" else "NO CYCLE" }.orEmpty()}
            """.trimMargin().trimBlankLines()
        }
}

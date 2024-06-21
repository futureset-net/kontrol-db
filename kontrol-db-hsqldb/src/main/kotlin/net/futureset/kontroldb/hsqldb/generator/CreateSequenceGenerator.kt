package net.futureset.kontroldb.hsqldb.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.trimBlankLines
import net.futureset.kontroldb.modelchange.CreateSequence
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class CreateSequenceGenerator(es: EffectiveSettings) : DbAwareGenerator<CreateSequence>(es) {

    override fun type() = CreateSequence::class

    override fun canApplyTo(es: EffectiveSettings): Boolean = es.databaseName == "hsqldb"

    override fun convertSingle(): CreateSequence.() -> String? = {
        """CREATE SEQUENCE ${schemaObject.toQuoted()}
                ${columnType?.let { "AS ${it.toQuoted()}" }.orEmpty()}
                START WITH $startWith
                ${incrementBy.takeUnless { it == 1L }?.let { "INCREMENT BY $it" }.orEmpty()}
                ${minValue?.let { "MINVALUE $it" }.orEmpty()}
                ${maxValue?.let { "MAXVALUE $it" }.orEmpty()}
                ${cycle?.let { if (it) "CYCLE" else "NO CYCLE" }.orEmpty()}
        """.trimMargin().trimBlankLines()
    }
}

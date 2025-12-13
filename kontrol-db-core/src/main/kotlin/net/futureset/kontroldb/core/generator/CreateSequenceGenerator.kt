package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.trimBlankLines
import net.futureset.kontroldb.modelchange.CreateSequence
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class CreateSequenceGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<CreateSequence>(es, CreateSequence::class) {
    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convertSingle(): CreateSequence.() -> String? = {
        """CREATE SEQUENCE ${schemaObject.name.toQuoted()}
                ${columnType?.let { "AS ${it.toQuoted()}" }.orEmpty()}
                START WITH $startWith
                ${incrementBy.takeUnless { it == 1L }?.let { "INCREMENT BY $it" }.orEmpty()}
                ${minValue?.let { "MINVALUE $it" }.orEmpty()}
                ${maxValue?.let { "MAXVALUE $it" }.orEmpty()}
                ${cycle?.let { if (it) "CYCLE" else "NO CYCLE" }.orEmpty()}
                ${cache.takeUnless { it == 0 }?.let { "CACHE $it" }.orEmpty()}
        """.trimMargin().trimBlankLines()
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorFactory
import net.futureset.kontroldb.modelchange.DatabaseConditionalChange
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class DatabaseConditionalChangeGenerator(es: EffectiveSettings, private val sqlGeneratorFactory: SqlGeneratorFactory) : DbAwareGenerator<DatabaseConditionalChange>(es, DatabaseConditionalChange::class) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun convert(change: DatabaseConditionalChange): List<String> {
        return if (change.dbPredicate(es.databaseName)) {
            sqlGeneratorFactory.generateSql(change.wrappedChange)
        } else {
            emptyList()
        }
    }
}

package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DatabaseConditionalChange
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlGenerator::class])
class DatabaseConditionalChangeGenerator(val db: EffectiveSettings) : DbAwareGenerator<DatabaseConditionalChange>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

    override fun type() = DatabaseConditionalChange::class

    override fun convert(change: DatabaseConditionalChange): List<String> {
        return if (change.dbPredicate(db.databaseName)) {
            generateSql(change.wrappedChange)
        } else {
            emptyList()
        }
    }
}

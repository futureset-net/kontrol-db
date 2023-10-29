package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.AddForeignKey
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class AddForeignKeyTemplate(db: EffectiveSettings) : DbAwareTemplate<AddForeignKey>(
    db,
    TemplatePriority.DEFAULT,
) {
    override fun type(): KClass<AddForeignKey> {
        return AddForeignKey::class
    }

    override fun convertToSingleStatement(change: AddForeignKey): String {
        return """
${change.table.toSql{"ALTER TABLE $it ADD"} }${change.constraintName.toSql{" CONSTRAINT $it"}} FOREIGN KEY(${forEach(change.columnMap.keys)}) 
REFERENCES ${change.foreignTable.toSql()}(${forEach(change.columnMap.values)})
        """.trimIndent()
    }
}

package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.AddForeignKey
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
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

    override fun convertSingle(): AddForeignKey.() -> String? = {
        """
        ${table.toSql { "ALTER TABLE $it ADD" }}${constraintName.toSql { " CONSTRAINT $it" }} FOREIGN KEY(${
            forEach(
                columnMap.keys,
            )
        })
        REFERENCES ${foreignTable.toSql()}(${forEach(columnMap.values)})
        """.trimIndent()
    }
}

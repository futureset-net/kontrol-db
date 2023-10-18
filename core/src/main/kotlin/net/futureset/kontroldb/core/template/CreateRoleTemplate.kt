package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.CreateRole
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateRoleTemplate(db: EffectiveSettings) : DbAwareTemplate<CreateRole>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<CreateRole> {
        return CreateRole::class
    }

    override fun convertToSingleStatement(change: CreateRole): String =
        change.roleName.toSql { "CREATE ROLE $it" }
}

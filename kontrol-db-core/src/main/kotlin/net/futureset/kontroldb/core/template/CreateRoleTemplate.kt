package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.CreateRole
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CreateRoleTemplate(db: EffectiveSettings) : DbAwareTemplate<CreateRole>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<CreateRole> {
        return CreateRole::class
    }

    override fun convertSingle(): CreateRole.() -> String? = {
        roleName.toSql { "CREATE ROLE $it" }
    }
}

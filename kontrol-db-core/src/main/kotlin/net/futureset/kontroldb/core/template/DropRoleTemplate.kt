package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.DropRole
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropRoleTemplate(db: EffectiveSettings) : DbAwareTemplate<DropRole>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropRole> {
        return DropRole::class
    }

    override fun convertSingle(): DropRole.() -> String? = {
        roleName.toSql { "DROP ROLE $it" }
    }
}

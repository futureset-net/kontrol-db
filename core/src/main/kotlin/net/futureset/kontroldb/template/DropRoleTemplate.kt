package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.DropRole
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class DropRoleTemplate(db: EffectiveSettings) : DbAwareTemplate<DropRole>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<DropRole> {
        return DropRole::class
    }

    override fun convertToSingleStatement(change: DropRole): String =
        change.roleName.toSql { "DROP ROLE $it" }
}

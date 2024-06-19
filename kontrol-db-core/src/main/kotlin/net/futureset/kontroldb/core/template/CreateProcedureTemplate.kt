package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.CreateProcedure
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class CreateProcedureTemplate(db: EffectiveSettings) :
    DbAwareTemplate<CreateProcedure>(db, TemplatePriority.DEFAULT) {
    override fun type() = CreateProcedure::class

    override fun convertSingle(): CreateProcedure.() -> String? = {
        (body ?: path?.text()).let {
            if (wholeDefinition) it else "CREATE $it"
        }
    }
}

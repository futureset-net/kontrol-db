package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.CreateView
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class CreateViewTemplate(db: EffectiveSettings) : DbAwareTemplate<CreateView>(db, TemplatePriority.DEFAULT) {
    override fun type() = CreateView::class

    override fun convertToSingleStatement(change: CreateView): String? {
        val body = change.body ?: change.path?.text()
        return if (change.wholeDefinition) body else "CREATE $body"
    }
}

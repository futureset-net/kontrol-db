package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CommentTemplate(db: EffectiveSettings) : DbAwareTemplate<Comment>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<Comment> {
        return Comment::class
    }

    override fun convertToSingleStatement(change: Comment): String {
        val text = change.text.trimEnd()
        return if (text.count { it == '\n' } > 0) {
            val lines = text.split("\n")
            val maxLineLength = lines.maxOfOrNull { it.length } ?: 0
            lines.joinToString(
                prefix = "/" + "*".repeat(maxLineLength + 1) + "\n* ",
                separator = "\n* ",
                postfix = "\n" + "*".repeat(maxLineLength + 1) + "/",
            )
        } else {
            change.text.split("\n").joinToString(prefix = "-- ", separator = "\n-- ")
        }
    }
}

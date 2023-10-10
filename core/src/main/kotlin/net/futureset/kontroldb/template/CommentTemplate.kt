package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.modelchange.sqlLogger
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class CommentTemplate(private val db: EffectiveSettings) : DbAwareTemplate<Comment>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<Comment> {
        return Comment::class
    }

    override fun convertToSingleStatement(change: Comment): String? {
        return if (change.text.count { it == '\n' } > 0) {
            val lines = change.text.split("\n")
            val maxLineLength = lines.maxOfOrNull { it.length } ?: 0
            change.text.split("\n")
                .joinToString(
                    prefix = "/" + "*".repeat(maxLineLength + 1) + "\n* ",
                    separator = "\n* ",
                    postfix = "\n" + "*".repeat(maxLineLength + 1) + "/",
                )
        } else {
            change.text.split("\n").joinToString(prefix = "-- ", separator = "\n-- ")
        }.let { comment ->
            if (db.isScripting) {
                comment
            } else {
                sqlLogger.info(comment)
                null
            }
        }
    }
}

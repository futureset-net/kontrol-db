package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class CommentTemplate(db: EffectiveSettings) : DbAwareTemplate<ScriptComment>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<ScriptComment> {
        return ScriptComment::class
    }

    override fun convertSingle(): ScriptComment.() -> String = {
        val text = text.trim()
        if (text.count { it == '\n' } > 0) {
            val lines = text.split("\n")
            val maxLineLength = lines.maxOfOrNull { it.length } ?: 0
            lines.joinToString(
                prefix = "/" + "*".repeat(maxLineLength + 1) + "\n* ",
                separator = "\n* ",
                postfix = "\n" + "*".repeat(maxLineLength + 1) + "/",
            )
        } else {
            text.split("\n").joinToString(prefix = "-- ", separator = "\n-- ")
        }
    }
}

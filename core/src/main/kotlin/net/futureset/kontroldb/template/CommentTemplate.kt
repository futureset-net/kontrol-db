package net.futureset.kontroldb.template

import net.futureset.kontroldb.DbAwareTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.modelchange.Comment
import net.futureset.kontroldb.settings.EffectiveSettings
import kotlin.reflect.KClass

class CommentTemplate(db: EffectiveSettings) : DbAwareTemplate<Comment>(db, TemplatePriority.DEFAULT) {
    override fun type(): KClass<Comment> {
        return Comment::class
    }

    override fun canApplyTo(effectiveSettings: EffectiveSettings): Boolean {
        return effectiveSettings.isScripting
    }

    override fun convert(change: Comment): String {
        if (change.text.contains("\n")) {
            return change.text.split("\n")
                .joinToString(prefix = "/*******************************\n* ", separator = "\n* ", postfix = "\n*******************************/")
        } else {
            return "-- ${change.text}"
        }
    }
}

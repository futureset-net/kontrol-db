package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlGenerator::class])
class CommentGenerator(db: EffectiveSettings) : DbAwareGenerator<ScriptComment>(db) {

    override val priority: GeneratorPriority = GeneratorPriority.DEFAULT

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

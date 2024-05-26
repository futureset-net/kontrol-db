package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder

data class ScriptComment(val text: String) : ModelChange, CommentMarker

/**
 * Generate a comment in SQL script
 *
 * @param comment the text the comment, multiple lines are supported
 */
fun ModelChangesBuilder.scriptComment(comment: String) = apply {
    changes.add(ScriptComment(comment))
}

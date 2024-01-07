package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.dsl.ModelChangesBuilder

data class Comment(val text: String) : ModelChange, CommentMarker

fun ModelChangesBuilder.comment(comment: String) = apply {
    changes.add(Comment(comment))
}

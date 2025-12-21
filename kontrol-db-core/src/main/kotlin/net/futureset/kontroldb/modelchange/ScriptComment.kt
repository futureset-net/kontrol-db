package net.futureset.kontroldb.modelchange

data class ScriptComment(
    val text: String,
) : ModelChange,
    CommentMarker

package net.futureset.kontroldb.modelchange

data class EndBanner(
    val message: String,
) : ModelChange,
    CommentMarker

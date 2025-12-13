package net.futureset.kontroldb.modelchange

data class StartBanner(
    val message: String,
) : ModelChange,
    CommentMarker

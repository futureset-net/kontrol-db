package net.futureset.kontroldb.modelchange

data class Comment(val text: String) : ModelChange, CommentMarker

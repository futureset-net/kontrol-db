package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange

data class EndBanner(val message: String) : ModelChange, CommentMarker

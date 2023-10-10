package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange

data class StartBanner(val message: String) : ModelChange, CommentMarker

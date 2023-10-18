package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange

data class ChangeToDefaultSchema(
    val message: String = "Change to default schema in settings",
) : ModelChange

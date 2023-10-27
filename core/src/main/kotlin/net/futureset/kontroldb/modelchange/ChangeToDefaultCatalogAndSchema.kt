package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange

data class ChangeToDefaultCatalogAndSchema(
    val message: String = "Change to default catalog and schema in settings",
) : ModelChange

package net.futureset.kontroldb.modelchange

data class ChangeToDefaultCatalogAndSchema(
    val message: String = "Change to default catalog and schema in settings",
) : ModelChange

package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.modelchange.InitCatalog
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class InitCatalogTemplateEmpty : EmptyTemplate<InitCatalog>(InitCatalog::class)

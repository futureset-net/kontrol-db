package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.modelchange.InitSchema
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class InitSchemaTemplateEmpty : EmptyTemplate<InitSchema>(InitSchema::class)

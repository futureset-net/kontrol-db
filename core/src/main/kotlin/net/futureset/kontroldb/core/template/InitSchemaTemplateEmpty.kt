package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.modelchange.InitSchema
import net.futureset.kontroldb.template.SqlTemplate
import org.koin.core.annotation.Singleton

@Singleton(binds = [SqlTemplate::class])
class InitSchemaTemplateEmpty : EmptyTemplate<InitSchema>(InitSchema::class)

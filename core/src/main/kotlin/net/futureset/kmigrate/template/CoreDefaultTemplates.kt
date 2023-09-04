package net.futureset.kmigrate.template

import net.futureset.kmigrate.SqlTemplate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreTemplateModule = module {

    singleOf(::CreateTableTemplate).bind(SqlTemplate::class)
    singleOf(::InsertRowTemplate).bind(SqlTemplate::class)
    singleOf(::UpdateRowTemplate).bind(SqlTemplate::class)
}

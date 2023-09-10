package net.futureset.kontroldb.template

import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.template.sqlserver.AddNotNullSqlServerTemplate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreTemplateModule = module {

    singleOf(::CreateTableTemplate).bind(SqlTemplate::class)
    singleOf(::InsertRowTemplate).bind(SqlTemplate::class)
    singleOf(::UpdateRowTemplate).bind(SqlTemplate::class)
    singleOf(::DropTableTemplate).bind(SqlTemplate::class)
    singleOf(::CommentTemplate).bind(SqlTemplate::class)
    singleOf(::AddPrimaryKeyTemplate).bind(SqlTemplate::class)
    singleOf(::AddForeignKeyTemplate).bind(SqlTemplate::class)
    singleOf(::CreateIndexTemplate).bind(SqlTemplate::class)
    singleOf(::AddNotNullTemplate).bind(SqlTemplate::class)
    singleOf(::AddNotNullSqlServerTemplate).bind(SqlTemplate::class)
}

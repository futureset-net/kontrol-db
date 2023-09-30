package net.futureset.kontroldb.template

import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.template.hsqldb.CreateTemporaryTableTemplate
import net.futureset.kontroldb.template.sqlserver.AddNotNullSqlServerTemplate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreTemplateModule = module {

    singleOf(::CreateTableTemplate).bind(SqlTemplate::class)
    singleOf(::InsertTemplate).bind(SqlTemplate::class)
    singleOf(::UpdateRowTemplate).bind(SqlTemplate::class)
    singleOf(::DropTableTemplate).bind(SqlTemplate::class)
    singleOf(::CommentTemplate).bind(SqlTemplate::class)
    singleOf(::AddPrimaryKeyTemplate).bind(SqlTemplate::class)
    singleOf(::AddForeignKeyTemplate).bind(SqlTemplate::class)
    singleOf(::CreateIndexTemplate).bind(SqlTemplate::class)
    singleOf(::AddNotNullTemplate).bind(SqlTemplate::class)
    singleOf(::AddNotNullSqlServerTemplate).bind(SqlTemplate::class)
    singleOf(::CreateRoleTemplate).bind(SqlTemplate::class)
    singleOf(::DropRoleTemplate).bind(SqlTemplate::class)
    singleOf(::GrantPermissionsTemplate).bind(SqlTemplate::class)
    singleOf(::ApplyDsvToTableTemplate).bind(SqlTemplate::class)
    singleOf(::InsertOrUpdateRowTemplate).bind(SqlTemplate::class)
    singleOf(::DeleteRowTemplate).bind(SqlTemplate::class)
    singleOf(::CreateTemporaryTableTemplate).bind(SqlTemplate::class)
    singleOf(::SelectQueryTemplate).bind(SqlTemplate::class)
    singleOf(::CreateProcedureTemplate).bind(SqlTemplate::class)
}

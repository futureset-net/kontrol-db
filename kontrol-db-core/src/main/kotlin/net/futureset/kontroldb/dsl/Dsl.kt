package net.futureset.kontroldb.dsl

import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.model.SchemaObjectBuilder
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.modelchange.AddColumns
import net.futureset.kontroldb.modelchange.AddForeignKey
import net.futureset.kontroldb.modelchange.AddNotNull
import net.futureset.kontroldb.modelchange.AddPrimaryKey
import net.futureset.kontroldb.modelchange.ApplyDsvToTable
import net.futureset.kontroldb.modelchange.ChangePermissions
import net.futureset.kontroldb.modelchange.CreateIndex
import net.futureset.kontroldb.modelchange.CreateProcedure
import net.futureset.kontroldb.modelchange.CreateRole
import net.futureset.kontroldb.modelchange.CreateSequence
import net.futureset.kontroldb.modelchange.CreateTable
import net.futureset.kontroldb.modelchange.CreateView
import net.futureset.kontroldb.modelchange.DeleteRows
import net.futureset.kontroldb.modelchange.DropColumns
import net.futureset.kontroldb.modelchange.DropIfExistsBuilder
import net.futureset.kontroldb.modelchange.DropIndexBuilder
import net.futureset.kontroldb.modelchange.DropRole
import net.futureset.kontroldb.modelchange.DropTable
import net.futureset.kontroldb.modelchange.ExportQueryBuilder
import net.futureset.kontroldb.modelchange.GrantOrRevoke
import net.futureset.kontroldb.modelchange.InsertOrUpdateRow
import net.futureset.kontroldb.modelchange.InsertRows
import net.futureset.kontroldb.modelchange.RenameColumn
import net.futureset.kontroldb.modelchange.RenameTable
import net.futureset.kontroldb.modelchange.ScriptComment
import net.futureset.kontroldb.modelchange.SelectQuery
import net.futureset.kontroldb.modelchange.UpdateRows

/**
 * Add columns to a table
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.addColumn
 * @param tableName name of the table to add columns
 * @param lambda containing column definitions
 * @receiver a container for all the changes
 * @return the immutable [AddColumns] type
 */
fun ModelChangesBuilder.addColumnsTo(
    tableName: String,
    lambda: AddColumns.AddColumnsBuilder.() -> Unit,
): AddColumns = AddColumns
    .AddColumnsBuilder(tableName)
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.addForeignKey(
    constraintName: String,
    lambda: AddForeignKey.AddForeignKeyBuilder.() -> Unit,
): AddForeignKey = AddForeignKey
    .AddForeignKeyBuilder()
    .apply(lambda)
    .constraintName(constraintName)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.addNotNull(lambda: AddNotNull.AddNotNullBuilder.() -> Unit): AddNotNull = AddNotNull
    .AddNotNullBuilder()
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.addPrimaryKey(
    constraintName: String,
    lambda: AddPrimaryKey.AddPrimaryKeyBuilder.() -> Unit,
): AddPrimaryKey = AddPrimaryKey
    .AddPrimaryKeyBuilder()
    .apply(lambda)
    .constraintName(constraintName)
    .build()
    .apply(changes::add)

/**
 * Load a delimiter separated file into a table
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.loadCsvFile
 * @param lambda DSL to configure what to load
 * @receiver [ModelChangesBuilder] a container for a collection of changes
 * @return [ApplyDsvToTable] the immutable data
 */
fun ModelChangesBuilder.applyDsvToTable(lambda: ApplyDsvToTable.ApplyDsvToTableBuilder.() -> Unit): ApplyDsvToTable = ApplyDsvToTable
    .ApplyDsvToTableBuilder()
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.grantPermissions(
    permission: String,
    vararg permissions: String,
    lambda: ChangePermissions.ChangePermissionsBuilder.() -> Unit,
): ChangePermissions = ChangePermissions
    .ChangePermissionsBuilder(GrantOrRevoke.GRANT, arrayListOf(permission, *permissions).toSet())
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.revokePermissions(
    permission: String,
    vararg permissions: String,
    lambda: ChangePermissions.ChangePermissionsBuilder.() -> Unit,
): ChangePermissions = ChangePermissions
    .ChangePermissionsBuilder(GrantOrRevoke.REVOKE, arrayListOf(permission, *permissions).toSet())
    .apply(lambda)
    .build()
    .apply(changes::add)

/**
 * Create an index
 *
 * @param indexName name of the index
 * @param lambda index attributes
 * @receiver [ModelChangesBuilder] DSL container
 * @return [CreateIndex] the immutable index object
 */
fun ModelChangesBuilder.createIndex(
    indexName: String,
    lambda: CreateIndex.CreateIndexBuilder.() -> Unit,
): CreateIndex = CreateIndex
    .CreateIndexBuilder(indexName = DbIdentifier(indexName))
    .apply(lambda)
    .build()
    .apply(changes::add)

/**
 * Create a stored procedure
 *
 * @param name of the procedure
 * @param lambda configure the procedure
 * @return [CreateProcedure]
 * @receiver [ModelChangesBuilder] container for a collection of changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createProcedure
 */
fun ModelChangesBuilder.createProcedure(
    name: String,
    lambda: CreateProcedure.CreateProcedureBuilder.() -> Unit,
) = CreateProcedure
    .CreateProcedureBuilder()
    .procedure { name(name) }
    .apply(lambda)
    .build()
    .also(changes::add)

/**
 * Create a database role
 *
 * @param roleName name of the role to create
 * @return [CreateRole] the immutable role data
 *
 * @receiver [ModelChangesBuilder] a container for a collection of model changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createRole
 */
fun ModelChangesBuilder.createRole(roleName: String): CreateRole = CreateRole
    .CreateRoleBuilder()
    .roleName(roleName)
    .build()
    .apply(changes::add)

/**
 * Create a sequence
 *
 * @param name the name of the sequence to create
 * @param lambda a block containing other sequence attributes
 * @return [CreateSequence]
 * @sample net.futureset.kontroldb.samples.AllSamples.createSequence
 */
fun ModelChangesBuilder.createSequence(
    name: String,
    lambda: CreateSequence.CreateSequenceBuilder.() -> Unit,
): CreateSequence = CreateSequence
    .CreateSequenceBuilder(name)
    .apply(lambda)
    .build()
    .also(changes::add)

/**
 * Create a table
 *
 * @param tableName the name of the table
 * @param lambda table columns and attributes
 * @receiver [ModelChangesBuilder] the container for changes
 * @return [CreateTable] the immutable data for the table definition
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createTable
 */
fun ModelChangesBuilder.createTable(
    tableName: String,
    lambda: CreateTable.CreateTableBuilder.() -> Unit,
): CreateTable = CreateTable
    .CreateTableBuilder()
    .table(tableName)
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.createTable(
    table: Table,
    lambda: CreateTable.CreateTableBuilder.() -> Unit,
): CreateTable = CreateTable
    .CreateTableBuilder()
    .table(table)
    .apply(lambda)
    .build()
    .apply(changes::add)

/**
 * Create a view
 *
 * @param viewName
 * @param lambda the view attributes
 * @receiver [ModelChangesBuilder] DSL container
 * @return [CreateView]
 */
fun ModelChangesBuilder.createView(
    viewName: String,
    lambda: CreateView.CreateViewBuilder.() -> Unit,
) = apply {
    CreateView
        .CreateViewBuilder(viewName)
        .apply(lambda)
        .build()
        .also(changes::add)
}

fun ModelChangesBuilder.deleteRowsFrom(
    tableName: String,
    block: DeleteRows.DeleteRowsBuilder.() -> Unit,
): DeleteRows = DeleteRows
    .DeleteRowsBuilder()
    .apply(block)
    .table(tableName)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.dropColumnsFrom(
    tableName: String,
    lambda: DropColumns.DropColumnsBuilder.() -> Unit,
): DropColumns = DropColumns
    .DropColumnsBuilder(tableName)
    .apply(lambda)
    .build()
    .apply(changes::add)

/**
 * Drop procedure if exists
 *
 * @param name procedure to drop
 * @param lambda configure other procedure options e.g. schema
 * @receiver [ModelChangesBuilder] collection of changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createProcedure
 */
fun ModelChangesBuilder.dropProcedureIfExists(
    name: String,
    lambda: SchemaObjectBuilder.() -> Unit = {},
) {
    changes.add(
        DropIfExistsBuilder()
            .objectName(name, lambda)
            .objectType(DbObjectType.PROCEDURE)
            .ifExists()
            .build(),
    )
}

fun ModelChangesBuilder.dropTableIfExists(
    name: String,
    lambda: SchemaObjectBuilder.() -> Unit = {},
) {
    changes.add(
        DropIfExistsBuilder()
            .objectName(name, lambda)
            .objectType(DbObjectType.TABLE)
            .ifExists()
            .build(),
    )
}

fun ModelChangesBuilder.dropViewIfExists(
    name: String,
    lambda: SchemaObjectBuilder.() -> Unit = {},
) {
    changes.add(
        DropIfExistsBuilder()
            .objectName(name, lambda)
            .objectType(DbObjectType.VIEW)
            .ifExists()
            .build(),
    )
}

/**
 * Drop sequence if exists
 *
 * @param name the name of the sequence
 * @param lambda sequence attributes
 * @receiver [ModelChangesBuilder] container for a set of changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createSequence
 */
fun ModelChangesBuilder.dropSequenceIfExists(
    name: String,
    lambda: SchemaObjectBuilder.() -> Unit = {},
) {
    changes.add(
        DropIfExistsBuilder()
            .objectName(name, lambda)
            .objectType(DbObjectType.SEQUENCE)
            .ifExists()
            .build(),
    )
}

fun ModelChangesBuilder.dropIndexIfExists(
    name: String,
    lambda: DropIndexBuilder.() -> Unit,
) {
    changes.add(DropIndexBuilder(name).ifExists().apply(lambda).build())
}

/**
 * Drop a database role
 *
 * @param roleName name of the role to drop
 * @return [DropRole] the immutable role data
 *
 * @receiver [ModelChangesBuilder] a container for a collection of model changes
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createRole
 */
fun ModelChangesBuilder.dropRole(roleName: String): DropRole = DropRole(roleName = DbIdentifier(roleName)).apply(changes::add)

/**
 * Drop a table
 *
 * @param tableName the name of the table
 * @param lambda other table attributes e.g. schema
 * @receiver [ModelChangesBuilder] container for the changes
 * @return [DropTable] the immutable data needed to drop the table
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.createTable
 */
fun ModelChangesBuilder.dropTable(
    tableName: String,
    lambda: DropTable.DropTableBuilder.() -> Unit = {},
): DropTable = DropTable
    .DropTableBuilder()
    .table(tableName)
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.dropTable(table: Table): DropTable = DropTable
    .DropTableBuilder()
    .table(table)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.exportData(lambda: ExportQueryBuilder.() -> Unit) = apply {
    changes.add(ExportQueryBuilder().apply(lambda).build())
}

fun ModelChangesBuilder.insertOrUpdateRowsOf(
    name: String,
    block: InsertOrUpdateRow.InsertOrUpdateRowBuilder.() -> Unit,
): InsertOrUpdateRow = InsertOrUpdateRow
    .InsertOrUpdateRowBuilder(name)
    .apply(block)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.insertRowsInto(
    tableName: String,
    block: InsertRows.InsertRowsBuilder.() -> Unit,
): InsertRows = InsertRows
    .InsertRowsBuilder()
    .table(tableName)
    .apply(block)
    .build()
    .apply(changes::add)

/**
 * Generate a comment in SQL script
 *
 * @param comment the text the comment, multiple lines are supported
 */
fun ModelChangesBuilder.scriptComment(comment: String) = apply {
    changes.add(ScriptComment(comment))
}

fun ModelChangesBuilder.select(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
    changes.add(SelectQuery.SelectQueryBuilder().apply(lambda).build())
}

fun ModelChangesBuilder.updateRowsOf(
    tableName: String,
    block: UpdateRows.UpdateRowsBuilder.() -> Unit,
): UpdateRows = UpdateRows
    .UpdateRowsBuilder()
    .apply(block)
    .table(tableName)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.renameTable(
    fromTableName: String,
    toTableName: String,
    lambda: RenameTable.RenameTableBuilder.() -> Unit = {},
): RenameTable = RenameTable
    .RenameTableBuilder(fromTableName, toTableName)
    .apply(lambda)
    .build()
    .apply(changes::add)

fun ModelChangesBuilder.renameColumn(
    tableName: String,
    lambda: RenameColumn.RenameColumnBuilder.() -> Unit = {},
): RenameColumn = RenameColumn
    .RenameColumnBuilder(tableName)
    .apply(lambda)
    .build()
    .apply(changes::add)

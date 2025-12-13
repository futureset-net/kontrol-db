package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnDefinition
import net.futureset.kontroldb.model.ColumnDefinition.ColumnDefinitionBuilder
import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.model.Tablespace

data class CreateTable(
    val table: Table,
    val columnDefinitions: List<ColumnDefinition>,
    val tablespace: Tablespace?,
    val primaryKey: AddPrimaryKey?,
    val fromSelect: SelectQuery?,
    val preserveRowsOnCommit: Boolean,
) : ModelChange {
    @KontrolDbDslMarker
    class CreateTableBuilder(
        private var tablespace: String? = null,
        private val columns: MutableList<ColumnDefinition> = mutableListOf(),
        private var primaryKey: AddPrimaryKey? = null,
        private var selectFrom: SelectQuery? = null,
        private var preserveRowsOnCommit: Boolean = true,
    ) : TableBuilder<CreateTableBuilder, CreateTable> {
        override lateinit var table: Table

        fun tablespace(tablespace: String) = apply {
            this.tablespace = tablespace
        }

        fun selectFrom(
            name: String,
            lambda: SelectQuery.SelectQueryBuilder.() -> Unit,
        ) = apply {
            selectFrom =
                SelectQuery
                    .SelectQueryBuilder()
                    .table(name)
                    .apply(lambda)
                    .build()
        }

        fun column(
            name: String,
            type: ColumnType,
            block: ColumnDefinitionBuilder.() -> Unit = { },
        ) = apply {
            ColumnDefinitionBuilder(name, type).apply(block).build().apply(columns::add)
        }

        fun primaryKey(
            constraintName: String,
            lambda: AddPrimaryKey.AddPrimaryKeyBuilder.() -> Unit,
        ) = apply {
            primaryKey =
                AddPrimaryKey
                    .AddPrimaryKeyBuilder()
                    .inline()
                    .table(this.table)
                    .apply(lambda)
                    .constraintName(constraintName)
                    .build()
        }

        override fun build(): CreateTable = CreateTable(
            table = table,
            tablespace = tablespace?.let(::Tablespace),
            columnDefinitions = columns,
            fromSelect = selectFrom,
            primaryKey = primaryKey,
            preserveRowsOnCommit = preserveRowsOnCommit,
        )
    }
}

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

package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ResourceResolver
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnDefinition
import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Resource
import net.futureset.kontroldb.model.Table

data class ApplyDsvToTable(
    val table: Table,
    val file: Resource,
    val useDbLoadingTool: Boolean,
    val headerMappings: Map<String, ColumnDefinition>,
    val primaryKeys: Set<DbIdentifier>,
    val separator: String,
    val deleteRows: Boolean,
    val updateRows: Boolean,
    val insertRows: Boolean,
    val ignoreInsertViolations: Boolean,
) : ModelChange {

    override fun checksum(resourceResolver: ResourceResolver): Int {
        return super.checksum(resourceResolver) + resourceResolver.resourceHash(file)
    }

    init {
        require(updateRows || insertRows || deleteRows) { "Must set at least one of deleteRows,updateRows or insertRows" }
        require(primaryKeys.isNotEmpty() || !(updateRows || insertRows)) {
            "Must specify primary key for insert or updates"
        }
        if (ignoreInsertViolations) {
            require(insertRows) {
                "Cannot ignore insert violations if insert rows is not set"
            }
        }
    }

    class ApplyDsvToTableBuilder : TableBuilder<ApplyDsvToTableBuilder, ApplyDsvToTable> {

        override lateinit var table: Table
        private var useDbLoadingTool = false
        private var headerMappings = mutableListOf<Pair<String, ColumnDefinition>>()
        private var primaryKeys = mutableSetOf<DbIdentifier>()
        private var separator = ","
        private var deleteRows = false
        private var updateRows = true
        private var insertRows = true
        private var ignoreInsertViolations = true
        private lateinit var file: Resource

        fun file(file: String) = apply {
            this.file = Resource.resource(file)
        }

        fun useDbLoadingTool(useDbLoadingTool: Boolean) = apply { this.useDbLoadingTool = useDbLoadingTool }
        fun columnMapping(
            columnName: String,
            columnType: ColumnType,
            headerName: String? = null,
            primaryKey: Boolean = false,
        ): ApplyDsvToTableBuilder =
            apply {
                headerMappings.add(
                    (headerName ?: columnName).uppercase() to ColumnDefinition(
                        DbIdentifier(columnName),
                        columnType,
                        nullable = true,
                    ),
                )
                if (primaryKey) {
                    primaryKeys.add(DbIdentifier(columnName))
                }
            }

        fun separator(separator: String) = apply { this.separator = separator }
        fun deleteRows(deleteRows: Boolean) = apply { this.deleteRows = deleteRows }
        fun updateRows(updateRows: Boolean) = apply { this.updateRows = updateRows }
        fun insertRows(insertRows: Boolean) = apply { this.insertRows = insertRows }
        fun ignoreInsertViolations(ignoreInsertViolations: Boolean) =
            apply { this.ignoreInsertViolations = ignoreInsertViolations }

        override fun build(): ApplyDsvToTable {
            return ApplyDsvToTable(
                table = table,
                file = file,
                useDbLoadingTool = useDbLoadingTool,
                headerMappings = headerMappings.associate { it },
                primaryKeys = primaryKeys,
                separator = separator,
                deleteRows = deleteRows,
                updateRows = updateRows,
                insertRows = insertRows,
                ignoreInsertViolations = ignoreInsertViolations,
            )
        }
    }
}

/**
 * Load a delimiter separated file into a table
 *
 * @sample net.futureset.kontroldb.samples.AllSamples.loadCsvFile
 * @param lambda DSL to configure what to load
 * @receiver [ModelChangesBuilder] a container for a collection of changes
 * @return [ApplyDsvToTable] the immutable data
 */
fun ModelChangesBuilder.applyDsvToTable(lambda: ApplyDsvToTable.ApplyDsvToTableBuilder.() -> Unit): ApplyDsvToTable =
    ApplyDsvToTable.ApplyDsvToTableBuilder().apply(lambda).build().apply(changes::add)

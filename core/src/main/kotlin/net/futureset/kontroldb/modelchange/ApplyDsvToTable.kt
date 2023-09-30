package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnDefinition
import net.futureset.kontroldb.ColumnType
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder
import java.nio.file.Files
import java.nio.file.Path

fun Path.contentsChecksum(): Int {
    var hashcode = 0
    Files.newInputStream(this).buffered(1024).use { data ->
        var currentByte: Int
        do {
            currentByte = data.read()
            hashcode = (hashcode + currentByte) % (Int.MAX_VALUE - 300)
        } while (currentByte != -1)
        return hashcode
    }
}

data class ApplyDsvToTable(
    val table: SchemaObject,
    val file: Path,
    val useDbLoadingTool: Boolean,
    val headerMappings: Map<String, ColumnDefinition>,
    val primaryKeys: Set<DbIdentifier>,
    val separator: String,
    val deleteRows: Boolean,
    val updateRows: Boolean,
    val insertRows: Boolean,
    val ignoreInsertViolations: Boolean,
) : ModelChange {

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

    override fun checksum(): Int {
        return super.checksum() + file.contentsChecksum()
    }

    data class ApplyDsvToTableBuilder(
        override var table: SchemaObject? = null,
    ) : TableBuilder<ApplyDsvToTableBuilder, ApplyDsvToTable> {

        private var useDbLoadingTool = false
        private var headerMappings = mutableListOf<Pair<String, ColumnDefinition>>()
        private var primaryKeys = mutableSetOf<DbIdentifier>()
        private var separator = ","
        private var deleteRows = false
        private var updateRows = true
        private var insertRows = true
        private var ignoreInsertViolations = true
        private var file: Path? = null

        fun file(file: Path) = apply {
            this.file = file
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
                table = requireNotNull(table) { "table must be specified for ApplyDsvToTable" },
                file = requireNotNull(file) { "file must be specified for ApplyDsvToTable" },
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

fun ModelChangesBuilder.applyDsvToTable(lambda: ApplyDsvToTable.ApplyDsvToTableBuilder.() -> Unit): ApplyDsvToTable =
    ApplyDsvToTable.ApplyDsvToTableBuilder().apply(lambda).build().apply(changes::add)

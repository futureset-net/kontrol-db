package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder
import net.futureset.kontroldb.Tablespace

data class CreateIndex(
    val table: SchemaObject,
    val columnReferences: List<DbIdentifier>,
    var indexName: DbIdentifier? = null,
    val tablespace: Tablespace? = null,
) : ModelChange {

    data class CreateIndexBuilder(
        override var table: SchemaObject? = null,
        private var indexName: DbIdentifier? = null,
        private var tablespace: String? = null,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
    ) : TableBuilder<CreateIndex> {

        fun indexName(indexName: String) = apply {
            this.indexName = DbIdentifier(indexName)
        }

        fun tablespace(tablespace: String) = apply {
            this.tablespace = tablespace
        }

        fun column(name: String) {
            columns.add(DbIdentifier(name))
        }

        override fun build(): CreateIndex {
            return CreateIndex(
                table = requireNotNull(table) { "Table not specified for Index" },
                indexName = indexName,
                tablespace = tablespace?.let(::Tablespace),
                columnReferences = columns,
            )
        }
    }
}

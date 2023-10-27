package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Table
import net.futureset.kontroldb.TableBuilder
import net.futureset.kontroldb.Tablespace

data class CreateIndex(
    val table: Table,
    val columnReferences: List<DbIdentifier>,
    val clustered: Boolean,
    val unique: Boolean,
    var indexName: DbIdentifier? = null,
    val tablespace: Tablespace? = null,
) : ModelChange {

    @KontrolDbDslMarker
    data class CreateIndexBuilder(
        private var indexName: DbIdentifier? = null,
        private var tablespace: String? = null,
        private var clustered: Boolean = false,
        private var unique: Boolean = false,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
    ) : TableBuilder<CreateIndexBuilder, CreateIndex> {
        override lateinit var table: Table
        fun indexName(indexName: String) = apply {
            this.indexName = DbIdentifier(indexName)
        }

        fun tablespace(tablespace: String) = apply {
            this.tablespace = tablespace
        }

        fun clustered(clustered: Boolean) = apply {
            this.clustered = clustered
        }

        fun unique(unique: Boolean) = apply {
            this.unique = unique
        }

        fun column(name: String) {
            columns.add(DbIdentifier(name))
        }

        override fun build(): CreateIndex {
            return CreateIndex(
                table = table,
                indexName = indexName,
                tablespace = tablespace?.let(::Tablespace),
                clustered = clustered,
                unique = unique,
                columnReferences = columns,
            )
        }
    }
}

fun ModelChangesBuilder.createIndex(lambda: CreateIndex.CreateIndexBuilder.() -> Unit): CreateIndex =
    CreateIndex.CreateIndexBuilder().apply(lambda).build().apply(changes::add)

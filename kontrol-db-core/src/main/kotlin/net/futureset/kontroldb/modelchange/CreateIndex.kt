package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Table
import net.futureset.kontroldb.model.Tablespace

data class CreateIndex(
    val table: Table,
    val columnReferences: List<DbIdentifier>,
    val clustered: Boolean,
    val unique: Boolean,
    val indexName: DbIdentifier?,
    val tablespace: Tablespace?,
) : ModelChange {
    @KontrolDbDslMarker
    class CreateIndexBuilder(
        private var indexName: DbIdentifier? = null,
        private var tablespace: String? = null,
        private var clustered: Boolean = false,
        private var unique: Boolean = false,
        private val columns: MutableList<DbIdentifier> = mutableListOf(),
    ) : TableBuilder<CreateIndexBuilder, CreateIndex> {
        override lateinit var table: Table

        fun tablespace(tablespace: String) = apply {
            this.tablespace = tablespace
        }

        fun unique(unique: Boolean) = apply {
            this.unique = unique
        }

        fun column(name: String) {
            columns.add(DbIdentifier(name))
        }

        override fun build(): CreateIndex = CreateIndex(
            table = table,
            indexName = indexName,
            tablespace = tablespace?.let(::Tablespace),
            clustered = clustered,
            unique = unique,
            columnReferences = columns,
        )
    }
}

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

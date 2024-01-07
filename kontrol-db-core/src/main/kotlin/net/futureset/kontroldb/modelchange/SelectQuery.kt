package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.model.ColumnAndValue
import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.Table

data class SelectQuery(
    val columns: List<ColumnAndValue>,
    val table: TableAlias?,
    val includeData: Boolean,
    val predicate: SqlPredicate? = null,

) : ModelChange {

    @KontrolDbDslMarker
    data class SelectQueryBuilder(
        private val columns: MutableList<ColumnAndValue> = mutableListOf(),
        private var includeData: Boolean = true,
        private var predicate: SqlPredicate? = null,
        override var alias: String? = null,
    ) : TableAliasBuilder<SelectQueryBuilder, SelectQuery> {

        override lateinit var table: Table

        fun column(columnName: String, expression: String? = null) = apply {
            columns.add(ColumnAndValue(DbIdentifier(columnName), expression?.let(ColumnValue::expression)))
        }

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        override fun build(): SelectQuery =
            SelectQuery(
                columns = columns,
                table = TableAlias(alias, table),
                predicate = predicate,
                includeData = this.includeData,
            )
    }
}

fun ModelChangesBuilder.select(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
    changes.add(SelectQuery.SelectQueryBuilder().apply(lambda).build())
}

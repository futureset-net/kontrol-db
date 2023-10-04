package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ColumnAlias
import net.futureset.kontroldb.ColumnAndValue
import net.futureset.kontroldb.ColumnValue
import net.futureset.kontroldb.DbIdentifier
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject

data class SelectQuery(
    val columns: List<ColumnAndValue>,
    val table: TableAlias?,
    val includeData: Boolean,
    val predicate: SqlPredicate? = null,

) : ModelChange {
    @KontrolDbDslMarker
    data class SelectQueryBuilder(
        var columns: MutableList<ColumnAndValue> = mutableListOf(),
        private var includeData: Boolean = true,
        private var predicate: SqlPredicate? = null,
        override var alias: String? = null,
    ) : TableAliasBuilder<SelectQueryBuilder, SelectQuery> {

        override lateinit var table: SchemaObject

        fun column(columnName: String, expression: String? = null) = apply {
            columns.add(ColumnAndValue(DbIdentifier(columnName), expression?.let(ColumnValue::expression)))
        }

        fun columnReference(columnName: String, alias: String? = null) =
            ColumnAlias(DbIdentifier(columnName), alias)

        fun excludeData() = apply {
            this.includeData = false
        }

        fun where(lambda: PredicateBuilder.() -> Unit) = apply {
            predicate = PredicateBuilder().apply(lambda).build()
        }

        fun includeData() = apply {
            this.includeData = true
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

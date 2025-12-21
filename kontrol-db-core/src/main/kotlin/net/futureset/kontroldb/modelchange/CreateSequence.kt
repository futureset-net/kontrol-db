package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.model.ColumnType
import net.futureset.kontroldb.model.DbIdentifier
import net.futureset.kontroldb.model.SchemaObject
import net.futureset.kontroldb.model.SchemaObjectBuilder

data class CreateSequence(
    val schemaObject: SchemaObject,
    val startWith: Long,
    val incrementBy: Long,
    val minValue: Long?,
    val maxValue: Long?,
    val cycle: Boolean?,
    val cache: Int,
    val columnType: ColumnType?,
) : ModelChange {
    class CreateSequenceBuilder(
        name: String,
    ) : Builder<CreateSequenceBuilder, CreateSequence> {
        private var createSequence =
            CreateSequence(
                schemaObject = SchemaObject(name = DbIdentifier(name)),
                startWith = 1,
                incrementBy = 1,
                minValue = null,
                maxValue = null,
                cycle = null,
                cache = 0,
                columnType = null,
            )

        /**
         * Other schema
         *
         * @param lambda
         * @receiver
         */
        fun otherSchema(lambda: SchemaObjectBuilder.() -> Unit) = apply {
            createSequence =
                createSequence.copy(
                    schemaObject = SchemaObjectBuilder(createSequence.schemaObject).apply(lambda).build(),
                )
        }

        fun startWith(startWith: Long) = apply {
            createSequence = createSequence.copy(startWith = startWith)
        }

        fun incrementBy(incrementBy: Long) = apply {
            createSequence = createSequence.copy(incrementBy = incrementBy)
        }

        fun columnType(columnType: ColumnType) = apply {
            createSequence = createSequence.copy(columnType = columnType)
        }

        fun minValue(minValue: Long) = apply {
            createSequence = createSequence.copy(minValue = minValue)
        }

        fun maxValue(maxValue: Long) = apply {
            createSequence = createSequence.copy(maxValue = maxValue)
        }

        fun cycle() = apply {
            createSequence = createSequence.copy(cycle = true)
        }

        fun cache(cache: Int) = apply {
            createSequence = createSequence.copy(cache = cache)
        }

        override fun build() = createSequence
    }
}

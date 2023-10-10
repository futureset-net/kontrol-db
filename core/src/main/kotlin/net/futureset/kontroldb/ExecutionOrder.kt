package net.futureset.kontroldb

import java.time.LocalDate

val EARLIEST_CHANGE = ExecutionOrder(LocalDate.of(1, 1, 1), "system")
val LATEST_CHANGE = ExecutionOrder(LocalDate.of(9999, 12, 9), "system", seq = 9999)

data class ExecutionOrder(
    val dateOfChange: LocalDate,
    val author: String,
    val seq: Short = 1,
) : Comparable<ExecutionOrder> {
    override fun compareTo(other: ExecutionOrder): Int {
        return Comparator.comparing(ExecutionOrder::dateOfChange)
            .thenComparing(ExecutionOrder::seq)
            .thenComparing(ExecutionOrder::author)
            .compare(this, other)
    }

    fun toSingleValue(): String {
        return "${dateOfChange.year}-${dateOfChange.monthValue}-${dateOfChange.dayOfMonth}-$seq-$author"
    }

    companion object {
        fun fromStringValue(s: String): ExecutionOrder {
            return s.split("-")
                .let { e ->
                    ExecutionOrder(
                        LocalDate.of(e[0].toInt(), e[1].toInt(), e[2].toInt()),
                        e[4],
                        e[3].toShort(),
                    )
                }
        }
    }

    fun beforeThis(): ExecutionOrder {
        return copy(seq = (this.seq + 10).toShort())
    }

    fun afterThis(): ExecutionOrder {
        return copy(seq = (this.seq + 10).toShort())
    }

    @KontrolDbDslMarker
    class ExecutionOrderBuilder : Builder<ExecutionOrderBuilder, ExecutionOrder> {
        private lateinit var dateOfChange: LocalDate
        private lateinit var author: String
        private var sequence: Short = 1

        infix fun author(author: String) = apply {
            this.author = author
        }

        infix fun sequence(sequence: Short) = apply {
            this.sequence = sequence
        }

        fun ymd(year: Int, month: Int, day: Int) = apply {
            this.dateOfChange = LocalDate.of(year, month, day)
        }

        fun executionOrder(lambda: ExecutionOrderBuilder.() -> Unit): ExecutionOrder {
            return ExecutionOrderBuilder().apply(lambda).build()
        }

        override fun build(): ExecutionOrder {
            return ExecutionOrder(
                author = author,
                seq = sequence,
                dateOfChange = dateOfChange,
            )
        }
    }
}

package net.futureset.kontroldb

import java.time.LocalDate

data class ExecutionOrder(
    val dateOfChange: LocalDate,
    val author: String,
    val seq: Int = 1,
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
                .let { e -> ExecutionOrder(LocalDate.of(e[0].toInt(), e[1].toInt(), e[2].toInt()), e[4], e[3].toInt()) }
        }
    }
}

data class ExecutionOrderBuilder(
    private var dateOfChange: LocalDate? = null,
    private var author: String? = null,
    private var sequence: Int = 1,
) : Builder<ExecutionOrder> {

    infix fun author(author: String) = apply {
        this.author = author
    }

    infix fun sequence(sequence: Int) = apply {
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
            author = requireNotNull(author),
            seq = sequence,
            dateOfChange = requireNotNull(dateOfChange),
        )
    }
}

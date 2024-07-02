package net.futureset.kontroldb.generator

interface Prioritised : Comparable<Prioritised> {
    val priority: GeneratorPriority

    override fun compareTo(other: Prioritised): Int {
        return priority.compareTo(other.priority)
    }
}

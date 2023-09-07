package net.futureset.kontroldb

import java.util.Objects
import kotlin.math.absoluteValue

abstract class Refactoring(
    val executionOrder: ExecutionOrder,
    val forward: List<ModelChange>,
    val rollback: List<ModelChange>,
    val executeMode: ExecuteMode = ExecuteMode.ONCE,
) : Comparable<Refactoring> {

    init {
        (forward + rollback).filterNot { it::class.isData }.run {
            require(isEmpty()) { "All ModelChanges must be data classes $this" }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Refactoring) {
            Objects.equals(id(), other.id()) && Objects.equals(this::class, other::class)
        }
        return false
    }

    override fun compareTo(other: Refactoring): Int {
        return Comparator.comparing(Refactoring::executionOrder).thenComparing(Refactoring::id).compare(this, other)
    }

    fun id(): String {
        return requireNotNull(this::class.qualifiedName) { "Refactoring must be a normal class" }
    }

    fun checkSum(): String {
        return (forward.hashCode()).absoluteValue.toString(16)
    }

    override fun hashCode(): Int {
        return Objects.hash(id())
    }
}

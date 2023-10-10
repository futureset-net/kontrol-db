package net.futureset.kontroldb

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

    open fun containsDdl(): Boolean {
        return forward.any { it.isDdl() }
    }

    override fun compareTo(other: Refactoring): Int {
        return Comparator.comparing(Refactoring::executionOrder).thenComparing(Refactoring::id).compare(this, other)
    }

    open fun id(): String {
        return requireNotNull(this::class.qualifiedName) { "Refactoring must be a normal class" }
    }

    open fun checkSum(): String {
        return forward.map(ModelChange::checksum).sum().absoluteValue.toString(16)
    }

    companion object {
        fun changes(vararg changes: ModelChange, lambda: ModelChangesBuilder.() -> Unit): List<ModelChange> {
            return changes.asList() + ModelChangesBuilder().apply(lambda).build()
        }

        fun executionOrder(lambda: ExecutionOrder.ExecutionOrderBuilder.() -> Unit): ExecutionOrder {
            return ExecutionOrder.ExecutionOrderBuilder().executionOrder(lambda)
        }
    }
}

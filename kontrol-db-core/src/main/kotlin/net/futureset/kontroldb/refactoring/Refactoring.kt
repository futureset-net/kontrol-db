package net.futureset.kontroldb.refactoring

import net.futureset.kontroldb.ResourceResolver
import net.futureset.kontroldb.dsl.ModelChangesBuilder
import net.futureset.kontroldb.modelchange.ModelChange
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

    override fun compareTo(other: Refactoring): Int = Comparator.comparing(Refactoring::executionOrder).thenComparing(Refactoring::id).compare(this, other)

    open fun id(): String = requireNotNull(this::class.qualifiedName) { "Refactoring must be a normal class" }

    open fun checkSum(resourceResolver: ResourceResolver): String = forward.sumOf { it.checksum(resourceResolver) }.absoluteValue.toString(16)

    companion object {
        fun changes(
            vararg changes: ModelChange,
            lambda: ModelChangesBuilder.() -> Unit,
        ): List<ModelChange> = changes.asList() + ModelChangesBuilder().apply(lambda).build()

        fun executionOrder(lambda: ExecutionOrder.ExecutionOrderBuilder.() -> Unit): ExecutionOrder = ExecutionOrder.ExecutionOrderBuilder().executionOrder(lambda)
    }
}

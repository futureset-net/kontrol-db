package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.model.ColumnValue
import net.futureset.kontroldb.model.SqlString
import net.futureset.kontroldb.settings.EffectiveSettings

interface SqlPredicate : SqlString {

    fun isEmpty(): Boolean = false
    operator fun plus(predicate: SqlPredicate): SqlPredicate {
        return if (isEmpty()) predicate else AllOf(listOf(this, predicate))
    }
}

interface Operand : SqlString {
    fun isNull() = false
}

data class AllOf(val predicates: List<SqlPredicate>) : SqlPredicate {

    override fun isEmpty() = predicates.isEmpty()

    override fun plus(predicate: SqlPredicate) =
        if (predicate.isEmpty()) {
            this
        } else if (predicate is AllOf) {
            this.copy(predicates = predicates.toMutableList() + predicate.predicates)
        } else {
            this.copy(predicates = predicates.toMutableList().apply { add(predicate) })
        }

    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return when (predicates.size) {
            0 -> ""
            1 -> predicates.first().toQuoted(effectiveSettings)
            else -> predicates.joinToString(separator = " AND ", prefix = "(", postfix = ")") {
                it.toQuoted(effectiveSettings)
            }
        }
    }
}

data class AnyOf(val predicates: List<SqlPredicate>) : SqlPredicate {

    override fun isEmpty() = predicates.isEmpty()
    override fun plus(predicate: SqlPredicate) =
        if (predicate.isEmpty()) {
            this
        } else if (predicate is AnyOf) {
            this.copy(predicates = predicates.toMutableList() + predicate.predicates)
        } else {
            this.copy(predicates = predicates.toMutableList().apply { add(predicate) })
        }

    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return when (predicates.size) {
            0 -> ""
            1 -> predicates.first().toQuoted(effectiveSettings)
            else -> predicates.joinToString(separator = " OR ", prefix = "(", postfix = ")") {
                it.toQuoted(effectiveSettings)
            }
        }
    }
}

data class Not(val predicate: SqlPredicate) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return "NOT ${predicate.toQuoted(effectiveSettings)}"
    }
}

data class Exists(private val selectQuery: SelectQuery) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings): String {
        return "EXISTS (${
            effectiveSettings.sqlGeneratorResolver.resolveGenerator(selectQuery)?.convert(selectQuery)
                ?.joinToString(separator = "\n")
        })"
    }
}

data class Eq(val a: Operand, val b: Operand) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings) =
        a.toQuoted(effectiveSettings) + (" = ".takeUnless { b.isNull() } ?: " IS ") + b.toQuoted(effectiveSettings)
}

data class Gt(val a: Operand, val b: Operand) : SqlPredicate {

    override fun toQuoted(effectiveSettings: EffectiveSettings) =
        a.toQuoted(effectiveSettings) + " > " + b.toQuoted(effectiveSettings)
}

data class Lt(val a: Operand, val b: Operand) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings) =
        a.toQuoted(effectiveSettings) + " < " + b.toQuoted(effectiveSettings)
}

data class Lte(val a: Operand, val b: Operand) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings) =
        a.toQuoted(effectiveSettings) + " <= " + b.toQuoted(effectiveSettings)
}

data class Gte(val a: Operand, val b: Operand) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings) =
        a.toQuoted(effectiveSettings) + " >= " + b.toQuoted(effectiveSettings)
}

data class Between(val subject: Operand, val a: Operand, val b: Operand) : SqlPredicate {
    override fun toQuoted(effectiveSettings: EffectiveSettings) =
        subject.toQuoted(effectiveSettings) + " BETWEEN " + a.toQuoted(effectiveSettings) + " AND " + b.toQuoted(
            effectiveSettings,
        )
}

@KontrolDbDslMarker
class PredicateBuilder(private var predicate: SqlPredicate = AllOf(predicates = emptyList())) :
    Builder<PredicateBuilder, SqlPredicate>, SqlValueFactory {

    fun existsSelect(vararg columnNames: String, lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
        predicate = Exists(
            SelectQuery.SelectQueryBuilder()
                .apply { columnNames.toList().forEach { column(it, null) } }
                .apply(lambda).build(),
        )
    }

    fun anyOf(lambda: PredicateBuilder.() -> Unit) = apply {
        predicate += PredicateBuilder(AnyOf(emptyList())).apply(lambda).build()
    }

    fun allOf(lambda: PredicateBuilder.() -> Unit) = apply {
        predicate += PredicateBuilder(AllOf(emptyList())).apply(lambda).build()
    }

    infix fun Operand.inRangeOf(operand: Pair<Any, Any>) = apply {
        predicate += Between(
            this,
            operand.first.let { if (it is Operand) it else ColumnValue.value(it) },
            operand.second.let { if (it is Operand) it else ColumnValue.value(it) },
        )
    }

    infix fun Operand.eq(operand: Operand) = apply {
        predicate += Eq(this, operand)
    }

    infix fun Operand.eq(operand: String) = apply {
        eq(ColumnValue(operand, true))
    }

    infix fun Operand.eq(operand: Any) = apply {
        eq(ColumnValue(operand, false))
    }

    infix fun Operand.gt(operand: Operand) = apply {
        predicate += Gt(this, operand)
    }

    infix fun Operand.gt(operand: String) = apply {
        gt(ColumnValue(operand, true))
    }

    infix fun Operand.gt(operand: Any) = apply {
        gt(ColumnValue(operand, false))
    }

    infix fun Operand.lt(operand: Operand) = apply {
        predicate += Lt(this, operand)
    }

    infix fun Operand.lt(operand: String) = apply {
        lt(ColumnValue(operand, true))
    }

    infix fun Operand.lt(operand: Any) = apply {
        lt(ColumnValue(operand, false))
    }

    infix fun Operand.lte(operand: Operand) = apply {
        predicate += Lte(this, operand)
    }

    infix fun Operand.lte(operand: String) = apply {
        lte(ColumnValue(operand, true))
    }

    infix fun Operand.lte(operand: Any) = apply {
        lte(ColumnValue(operand, false))
    }

    infix fun Operand.gte(operand: Operand) = apply {
        predicate += Gte(this, operand)
    }

    infix fun Operand.gte(operand: String) = apply {
        gte(ColumnValue(operand, true))
    }

    infix fun Operand.gte(operand: Any) = apply {
        gte(ColumnValue(operand, false))
    }

    fun not(lambda: PredicateBuilder.() -> Unit) = apply {
        predicate += Not(PredicateBuilder().apply(lambda).build())
    }

    override fun build(): SqlPredicate = predicate
}

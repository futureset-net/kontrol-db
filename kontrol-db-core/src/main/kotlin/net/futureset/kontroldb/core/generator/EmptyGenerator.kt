package net.futureset.kontroldb.core.generator

import net.futureset.kontroldb.generator.GeneratorPriority
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.generator.SqlGeneratorResolver
import net.futureset.kontroldb.modelchange.ModelChange
import kotlin.reflect.KClass

abstract class EmptyGenerator<T : ModelChange>(override val type: KClass<T>) : SqlGenerator<T> {
    override val priority = GeneratorPriority.DEFAULT

    override lateinit var sqlGeneratorResolver: SqlGeneratorResolver

    override fun convert(change: T) = emptyList<String>()
}

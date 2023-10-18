package net.futureset.kontroldb.core.template

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SqlTemplate
import net.futureset.kontroldb.TemplatePriority
import net.futureset.kontroldb.TemplateResolver
import kotlin.reflect.KClass

abstract class EmptyTemplate<T : ModelChange>(private val clazz: KClass<T>) : SqlTemplate<T> {
    override val priority: TemplatePriority = TemplatePriority.DEFAULT

    override lateinit var templateResolver: TemplateResolver
    override fun type(): KClass<T> = clazz
    override fun convert(change: T) = emptyList<String>()
}

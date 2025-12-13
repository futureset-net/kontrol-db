package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ResourceResolver

interface ModelChange {
    fun getName(): String = javaClass.simpleName

    fun checksum(resourceResolver: ResourceResolver) = hashCode()
}

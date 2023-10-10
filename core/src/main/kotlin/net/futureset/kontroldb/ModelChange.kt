package net.futureset.kontroldb

interface ModelChange {

    fun getName(): String = javaClass.simpleName

    fun checksum() = hashCode()

    fun isDdl() = true
}

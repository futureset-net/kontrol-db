package net.futureset.kmigrate

interface ModelChange {

    fun getName(): String = javaClass.simpleName
}

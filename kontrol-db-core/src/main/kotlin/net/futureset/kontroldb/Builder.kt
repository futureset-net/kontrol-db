package net.futureset.kontroldb

interface Builder<B : Builder<B, T>, T> {
    fun build(): T
}

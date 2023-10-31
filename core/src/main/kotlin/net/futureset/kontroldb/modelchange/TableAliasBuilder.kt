package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.model.Table

@Suppress("UNCHECKED_CAST")
interface TableAliasBuilder<B : TableBuilder<B, T>, T : ModelChange> : TableBuilder<B, T> {
    var alias: String?

    fun alias(alias: String): B = apply {
        this.alias = alias
    } as B

    fun tableWithAlias(table: Table, alias: String): B {
        alias(alias)
        return super.table(table)
    }

    fun tableWithAlias(table: String, alias: String): B {
        alias(alias)
        return super.table(table) {}
    }
}

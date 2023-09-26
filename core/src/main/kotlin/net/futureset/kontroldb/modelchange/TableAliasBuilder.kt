package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.SchemaObject
import net.futureset.kontroldb.TableBuilder

interface TableAliasBuilder<B : TableBuilder<B, T>, T : ModelChange> : TableBuilder<B, T> {
    var alias: String?

    fun alias(alias: String): B = apply {
        this.alias = alias
    } as B

    fun tableWithAlias(table: SchemaObject, alias: String): B {
        alias(alias)
        return super.table(table)
    }

    fun tableWithAlias(table: String, alias: String): B {
        alias(alias)
        return super.table(table)
    }
}

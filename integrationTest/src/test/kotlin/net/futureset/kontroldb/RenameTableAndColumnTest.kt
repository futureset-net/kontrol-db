package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.dsl.createTable
import net.futureset.kontroldb.dsl.dropTable
import net.futureset.kontroldb.dsl.renameColumn
import net.futureset.kontroldb.dsl.renameTable
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.refactoring.Refactoring
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class RenameTableAndColumnTest {
    @Test
    fun `Can rename a column and a table`() {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    single { CreateTableToRename() }.bind(Refactoring::class)
                },
            )
        }.use {
            // apply the forward changes (create table, rename column, rename table)
            val applied = it.applySql()
            assertThat(applied).isGreaterThanOrEqualTo(3)

            // Verify the renamed table and column exist by selecting from it
            it.applySqlDirectly.withConnection { conn ->
                val quotedTable = it.effectiveSettings.run { quote("RENAMED_CUSTOMER") }
                val sql = it.effectiveSettings.run { "SELECT ${quote("NEW_NAME")} FROM $quotedTable" }
                conn.executeQuery(sql) { rs ->
                    // no rows expected but query should execute
                    assertThat(rs.metaData.columnCount).isGreaterThanOrEqualTo(1)
                }
            }
        }
    }

    class CreateTableToRename :
        Refactoring(
            executionOrder {
                ymd(2023, 12, 1)
                author("test")
            },
            forward =
            changes {
                createTable("CUSTOMER_TO_RENAME") {
                    column("CUST_ID", Varchar(10))
                    column("OLD_NAME", Varchar(20))
                }
                renameColumn("CUSTOMER_TO_RENAME") {
                    oldName("OLD_NAME")
                    newName("NEW_NAME")
                }
                renameTable("CUSTOMER_TO_RENAME", "RENAMED_CUSTOMER")
            },
            rollback = changes {
                dropTable("RENAMED_CUSTOMER")
            },
        )
}

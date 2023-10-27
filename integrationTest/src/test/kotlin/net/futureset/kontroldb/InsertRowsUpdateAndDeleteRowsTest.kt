package net.futureset.kontroldb

import net.futureset.kontroldb.ColumnValue.Companion.value
import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import net.futureset.kontroldb.StandardColumnTypes.INT_32
import net.futureset.kontroldb.modelchange.PredicateBuilder
import net.futureset.kontroldb.modelchange.UpdateMode
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.deleteRows
import net.futureset.kontroldb.modelchange.executeQuery
import net.futureset.kontroldb.modelchange.insertOrUpdateRow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@ExtendWith(DatabaseProvision::class)
internal class InsertRowsUpdateAndDeleteRowsTest {

    class CreateATable(param: PredicateBuilder.() -> Unit) : Refactoring(
        executionOrder {
            ymd(2023, 9, 24)
            author("ben")
        },
        forward = changes {
            createTable {
                table("fred")
                column("TEST_COLUMN", INT_32)
            }
            insertOrUpdateRow {
                table("fred")
                for (i in 1..100) {
                    values {
                        value("TEST_COLUMN", i)
                    }
                }
                primaryKey("TEST_COLUMN")
                mode(UpdateMode.INSERT)
            }
            deleteRows {
                table("fred")
                alias("B")
                where(param)
            }
        },
        rollback = emptyList(),
    )

    data class Param(
        val expectedResultCount: Int,
        val selectQuery: PredicateBuilder.() -> Unit,
    )

    @ParameterizedTest
    @MethodSource("fred")
    fun `Check various predicates`(param: Param) {
        dsl {
            loadConfig("test-config.yml")
            changeModules(
                module {
                    singleOf(InsertRowsUpdateAndDeleteRowsTest::CreateATable).bind(Refactoring::class)
                    single { param.selectQuery }
                },
            )
        }.use { engine ->

            engine.applySql()

            assertThat(
                engine.applySqlDirectly.withConnection {
                    it.executeQuery("""SELECT COUNT(*) FROM "fred"""") { rs ->
                        rs.getInt(1)
                    }.first()
                },
            ).isEqualTo(param.expectedResultCount)
        }
    }

    companion object {
        @JvmStatic
        fun fred() = listOf(
            Param(99) {
                col("TEST_COLUMN") eq 5
            },
            Param(95) {
                col("TEST_COLUMN") lt 6
            },
        )
    }
}

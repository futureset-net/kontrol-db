package net.futureset.kontroldb.samples

import net.futureset.kontroldb.model.DbObjectType
import net.futureset.kontroldb.model.StandardColumnTypes
import net.futureset.kontroldb.model.StandardColumnTypes.BOOLEAN
import net.futureset.kontroldb.model.StandardColumnTypes.DATE
import net.futureset.kontroldb.model.StandardColumnTypes.Decimal
import net.futureset.kontroldb.model.StandardColumnTypes.INT16
import net.futureset.kontroldb.model.StandardColumnTypes.INT32
import net.futureset.kontroldb.model.StandardColumnTypes.INT64
import net.futureset.kontroldb.model.StandardColumnTypes.LOCALDATETIME
import net.futureset.kontroldb.model.StandardColumnTypes.Varchar
import net.futureset.kontroldb.modelchange.addColumnsTo
import net.futureset.kontroldb.modelchange.applyDsvToTable
import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.modelchange.createRole
import net.futureset.kontroldb.modelchange.createSequence
import net.futureset.kontroldb.modelchange.createTable
import net.futureset.kontroldb.modelchange.dropColumnsFrom
import net.futureset.kontroldb.modelchange.dropIndexIfExists
import net.futureset.kontroldb.modelchange.dropProcedureIfExists
import net.futureset.kontroldb.modelchange.dropRole
import net.futureset.kontroldb.modelchange.dropSequenceIfExists
import net.futureset.kontroldb.modelchange.dropTable
import net.futureset.kontroldb.modelchange.dropTableIfExists
import net.futureset.kontroldb.modelchange.grantPermissions
import net.futureset.kontroldb.refactoring.Refactoring

@Suppress("unused")
object AllSamples {
    fun loadCsvFile() {
        class LoadACsvFile :
            Refactoring(
                executionOrder {
                    ymd(2023, 9, 16)
                    author("ben")
                    sequence(1)
                },
                forward =
                changes {
                    applyDsvToTable {
                        useDbLoadingTool(false)
                        file("path/to/file.csv")
                        insertRows(true)
                        deleteRows(true)
                        updateRows(true)
                        ignoreInsertViolations(false)
                        separator("|")
                        table("CUSTOMER")
                        columnMapping("CUST_ID", INT64, primaryKey = true)
                        columnMapping("FIRSTNAME", Varchar(256))
                        columnMapping("LASTNAME", Varchar(256))
                        columnMapping("FAVOURITE_LETTER", StandardColumnTypes.Char(1))
                        columnMapping("FAVOURITE_DECIMAL", Decimal(3, 2))
                        columnMapping("IS_AN_IDIOT", BOOLEAN)
                        columnMapping("NUMBER_OF_STAMPS", INT16)
                        columnMapping("DATE_OF_BIRTH", DATE)
                        columnMapping("TIME_RIGHT_NOW", LOCALDATETIME)
                    }
                },
                rollback = emptyList(),
            )
    }

    fun grantPermissions() {
        class GrantPermissions :
            Refactoring(
                executionOrder {
                    ymd(2023, 9, 29)
                    author("ben")
                },
                forward =
                changes {
                    grantPermissions("INSERT", "UPDATE", "DELETE") {
                        on("CUSTOMER")
                        objectType(DbObjectType.TABLE)
                        to("A_NEW_ROLE")
                    }
                },
                rollback = emptyList(),
            )
    }

    fun dropSomeObjects() {
        class DropSomeObjects :
            Refactoring(
                executionOrder {
                    ymd(2023, 9, 13)
                    author("ben")
                },
                forward =
                changes {
                    dropIndexIfExists("IX_LASTNAME") {
                        table("CUSTOMER")
                    }
                    dropTableIfExists("CUSTOMER")
                    dropTableIfExists("NON_EXISTENT")
                },
                rollback = emptyList(),
            )
    }

    fun createSequence() {
        class CreateASequence :
            Refactoring(
                executionOrder {
                    ymd(2023, 9, 13)
                    author("ben")
                },
                forward =
                changes {
                    createSequence("MY_SEQUENCE") {
                        cache(10)
                        cycle()
                        incrementBy(2)
                        startWith(3)
                    }
                },
                rollback =
                changes {
                    dropSequenceIfExists("MY_SEQUENCE")
                },
            )
    }

    fun createRole() {
        class CreateARole :
            Refactoring(
                executionOrder {
                    ymd(2023, 9, 13)
                    author("ben")
                },
                forward =
                changes {
                    createRole("FRED")
                },
                rollback =
                changes {
                    dropRole("FRED")
                },
            )
    }

    fun createProcedure() {
        class CreateAProcedure :
            Refactoring(
                executionOrder {
                    ymd(2023, 11, 30)
                    author("ben")
                },
                forward =
                changes {
                    createProcedure("NEW_CUSTOMER") {
                        body(
                            """
                                CREATE PROCEDURE NEW_CUSTOMER
                                    @firstname VARCHAR(50),
                                    @lastname VARCHAR(50),
                                    @address VARCHAR(100) AS
                                BEGIN
                                INSERT INTO CUSTOMER(CUST_ID,FIRSTNAME,LASTNAME,ADDRESS,CITY,STATE,ZIP)
                                                VALUES (1, @firstname, @lastname, @address, 'LDN', 'NY', '123');
                                END
                            """.trimIndent(),
                        )
                        wholeDefinition(true)
                    }.onlyIfDatabase { it == "sqlserver" }
                    createProcedure("NEW_CUSTOMER") {
                        body(
                            """
                                CREATE PROCEDURE NEW_CUSTOMER(IN firstname VARCHAR (50), IN lastname VARCHAR (50), IN address VARCHAR (100))
                                MODIFIES SQL DATA
                                BEGIN ATOMIC
                                INSERT INTO CUSTOMER(CUST_ID,FIRSTNAME,LASTNAME,ADDRESS,CITY,STATE,ZIP)
                                        VALUES (1, firstname, lastname, address, 'LDN', 'NY', '123');
                                END
                            """.trimIndent(),
                        )
                        wholeDefinition(true)
                    }.onlyIfDatabase { it == "hsqldb" }
                    createProcedure("NEW_CUSTOMER") {
                        body(
                            """
                                CREATE PROCEDURE "NEW_CUSTOMER"(
                                    firstname VARCHAR (50),
                                    lastname VARCHAR (50),
                                    address VARCHAR (100))
                                    LANGUAGE SQL AS $$
                                INSERT INTO "CUSTOMER"("CUST_ID", "FIRSTNAME", "LASTNAME", "ADDRESS", "CITY", "STATE", "ZIP")
                                VALUES (1, firstname, lastname, address, 'LDN', 'NY', '123');
                                $$
                            """.trimIndent(),
                        )
                        wholeDefinition(true)
                    }.onlyIfDatabase { it == "postgres" }
                },
                rollback =
                changes {
                    dropProcedureIfExists("NEW_CUSTOMER")
                },
            )
    }

    fun addColumn() {
        class AddAColumn(
            schemaName: String,
        ) : Refactoring(
            executionOrder {
                ymd(2023, 11, 24)
                author("ben")
                sequence(2)
            },
            forward =
            changes {
                addColumnsTo("FRED") {
                    column("TWO_COLUMN", INT32)
                    column("THREE_COLUMN", INT32)
                    column("FOUR_COLUMN", INT32)
                    column("FIVE_COLUMN", INT32)
                    table {
                        schema(schemaName)
                    }
                }
                dropColumnsFrom("FRED") {
                    column("FOUR_COLUMN")
                    column("FIVE_COLUMN")
                    table {
                        schema(schemaName)
                    }
                }
            },
            rollback = emptyList(),
        )
    }

    fun createTable() {
        class CreateATable :
            Refactoring(
                executionOrder {
                    ymd(2023, 9, 13)
                    author("ben")
                },
                forward =
                changes {
                    createTable("MY_TABLE") {
                        asLocalTemporaryTable() // make temporary
                        column("CUST_ID", INT32)
                        column("FIRSTNAME", Varchar(20))
                        column("LASTNAME", Varchar(25))
                        column("ADDRESS", Varchar(32))
                        column("CITY", Varchar(20))
                        column("STATE", Varchar(2))
                        column("ZIP", Varchar(9))
                        primaryKey("CUSTOMER_PK") {
                            column("CUST_ID")
                        }
                    }
                },
                rollback =
                changes {
                    dropTable("MY_TABLE")
                },
            )
    }
}

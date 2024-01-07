package net.futureset.kontroldb.samples

import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.modelchange.dropProcedureIfExists
import net.futureset.kontroldb.refactoring.Refactoring

class CreateAProcedure : Refactoring(
    executionOrder {
        ymd(2023, 11, 30)
        author("ben")
    },
    forward = changes {
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
    rollback = changes {
        dropProcedureIfExists("NEW_CUSTOMER")
    },
)

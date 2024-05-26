---
title: createProcedure
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[createProcedure](create-procedure.html)



# createProcedure



[core engine and default templates for kontrol-db]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[createProcedure](create-procedure.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: [CreateProcedure.CreateProcedureBuilder](-create-procedure/-create-procedure-builder/index.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [CreateProcedure](-create-procedure/index.html)



Create a stored procedure



#### Return



[CreateProcedure](-create-procedure/index.html)



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) container for a collection of changes



#### Parameters


core engine and default templates for kontrol-db

| | |
|---|---|
| name | of the procedure |
| lambda | configure the procedure |



#### Samples

```kotlin
import net.futureset.kontroldb.modelchange.createProcedure
import net.futureset.kontroldb.modelchange.dropProcedureIfExists
import net.futureset.kontroldb.refactoring.Refactoring
fun main() { 
   //sampleStart 
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
   //sampleEnd
}
```




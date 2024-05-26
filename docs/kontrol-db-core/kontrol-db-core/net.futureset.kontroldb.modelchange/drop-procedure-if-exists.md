---
title: dropProcedureIfExists
---
//[kontrol-db-core](../../index.html)/[net.futureset.kontroldb.modelchange](index.html)/[dropProcedureIfExists](drop-procedure-if-exists.html)



# dropProcedureIfExists



[jvm]\
fun [ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html).[dropProcedureIfExists](drop-procedure-if-exists.html)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), lambda: SchemaObjectBuilder.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})



Drop procedure if exists



#### Receiver



[ModelChangesBuilder](../net.futureset.kontroldb.dsl/-model-changes-builder/index.html) collection of changes



#### Parameters


jvm

| | |
|---|---|
| name | procedure to drop |
| lambda | configure other procedure options e.g. schema |



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




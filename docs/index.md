---
title: All modules
---
/



# Module kontrol-db



##  Overview



A database migration tool.



Use to gain version-controlled change to your database.



Other such tools are Flyway and Liquibase.



This tool uses a Kotlin DSL to define the changes. If you want a tool that just applies raw SQL scripts, then Flyway would be the preferred solution. This solution deliberately avoids raw scripts to gain the advantages of a Kotlin DSL.



Such advantages of a Kotlin DSL are



- 
   Type safety - IDE auto-completion and compile-time checking
- 
   Deployment - Deploy as you would any other Kotlin code.
- 
   Multiple databases - The same migration can be applied to multiple databases. This is particularly useful if you use an in-memory database for development and a full RDBMS in production.
- 
   Extensibility - Custom migration steps can be developed and reused. For example, a complex sequence of SQL steps you might want to reuse in multiple migrations on different entities.




Here is an example of a migration class :

```kotlin
@Single
class AddPrimaryKey : Refactoring(
    executionOrder {
        ymd(2023, 9, 29)
        author("ben")
    },
    forward = changes {
        addPrimaryKey {
            table("CUSTOMER")
            column("CUST_ID")
            constraintName("PK_CUSTOMER")
        }
    },
    rollback = emptyList(),
)
```


Each class is independent of any other, no central code needs to be edited to add reference to the new class. This avoids any merge conflicts that could occur with multiple developers working on the same project.



The classpath is scanned (at compile time) and the @Single annotation ensures the step is included in the migration.



## All modules:


| Name |
|---|
| [kontrol-db-core](kontrol-db-core/index.html) | The core module of the KontrolDB database migration tool. Needs to be combined with a database module to be useful. |
| [kontrol-db-hsqldb](kontrol-db-hsqldb/index.html) | The HSQLDB module of the KontrolDB database migration tool. |
| [kontrol-db-sqlserver](kontrol-db-sqlserver/index.html) | The SQL Server module of the KontrolDB database migration tool. |


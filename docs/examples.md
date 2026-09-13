Examples index — Kontrol DB

This page collects short runnable examples (recipes) and points to the corresponding package/source so you can try them quickly. Each example has a one-line description and a short hint on how to run or adapt it.

Samples in this repository

### AllSamples (integrationTest)
A curated set of small refactorings and recipes implemented as Kotlin functions in integrationTest/src/main/kotlin/net/futureset/kontroldb/samples/AllSamples.kt. These are intentionally compact, showing common operations using the DSL.

- Source: https://github.com/futureset-net/kontrol-db/blob/main/integrationTest/src/main/kotlin/net/futureset/kontroldb/samples/AllSamples.kt

Examples (headings are anchor links)

#### Load CSV file into a table
Short: Demonstrates applying a delimited file to a table with insert/update/delete rules and column mapping.
How to use: open the AllSamples.loadCsvFile function and copy the Refactoring block into your module (annotate as a single Koin/Spring component), then run KontrolDbEngine to apply.

#### Grant permissions example
Short: Shows granting INSERT/UPDATE/DELETE to a role for a specific table.
How to use: reuse the grantPermissions block inside a Refactoring to apply role/permission changes in a migration.

#### Create/drop objects example
Short: Examples of dropTableIfExists, dropIndexIfExists and safe object removals.
How to use: Good as a template for idempotent drop operations in migrations.

#### Sequences, roles, procedures, add/drop columns, create table
Short: Collection of small recipes (createSequence, createRole, createProcedure, addColumnsTo, dropColumnsFrom, createTable) demonstrating dialect-specific options where appropriate.
How to use: Inspect the individual functions in AllSamples and adapt the change builders into your own Refactoring classes.

Running and experimenting

- The samples are included as Dokka samples and appear in the API docs under the Samples package — view the package page and source links in the generated site.
- To run an example locally: create a small Kotlin main that builds a KontrolDbEngine (KontrolDbEngineBuilder.dsl { ... }), include the Refactoring class on the classpath (or instantiate it inline), and call engine.applySql() or engine.generateSql(outputDirectory).

If you'd like a runnable project example added here, open an issue describing the scenario (e.g., "Standalone quickstart: create table + insert + rollback") and it will be added as a runnable sample.

Notes

- Many examples include database-specific variants (see createProcedure examples for HSQLDB, Postgres, SQL Server inside AllSamples). Use the dialect() setting in KontrolDbEngineBuilder to choose which variant applies.
- For direct links to code, use the GitHub source link above and the API Reference package pages in the generated docs.
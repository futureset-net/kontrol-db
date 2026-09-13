# AGENTS.md

## Project overview
- `kontrol-db` is a Kotlin DSL-based database migration engine.
- Multi-module Gradle build: `kontrol-db-core` (engine + DSL + orchestration), `kontrol-db-hsqldb`, `kontrol-db-postgres`, `kontrol-db-sqlserver`, `kontrol-db-oracle`, and `integrationTest` shared fixtures.
- Keep all migration primitives and core behavior in `kontrol-db-core`; keep dialect-specific SQL generation and registry logic inside the matching dialect module.

## Architecture and data flow
- Engine bootstrap happens in `kontrol-db-core/.../KontrolDbEngineBuilder.kt`: it creates a Koin container, loads `CoreModule()`, includes dialect modules, registers all `SqlGenerator`s, and builds a `KontrolDbEngine`.
- Dialect modules are registered with Koin using `@Module` + `@ComponentScan`, e.g. `kontrol-db-postgres/.../PostgresModule.kt`, `kontrol-db-sqlserver/.../SqlserverModule.kt`.
- Refactorings are explicit migration steps: extend `Refactoring` and provide `executionOrder`, `forward`, and `rollback`. The base class also enforces that all `ModelChange` entries are data classes.
- Prefer DSL-built `ModelChange` lists via `ModelChangesBuilder` over ad-hoc SQL strings; this is the project’s default extension pattern.

## Repository conventions
- Use explicit package names and types; keep logic small and composable.
- New `ModelChange` implementations should be `data class`es because `Refactoring` checks `it::class.isData`.
- `Refactoring.changes(...)` and `ModelChangesBuilder.onlyIfDatabase(...)` are the canonical patterns for composing conditional change sets.
- `KontrolDbEngineBuilder.dsl { ... }` is the normal entry point for creating an engine in code.

## Key files to read first
- `kontrol-db-core/src/main/kotlin/net/futureset/kontroldb/KontrolDbEngineBuilder.kt`
- `kontrol-db-core/src/main/kotlin/net/futureset/kontroldb/refactoring/Refactoring.kt`
- `kontrol-db-core/src/main/kotlin/net/futureset/kontroldb/dsl/ModelChangesBuilder.kt`
- `integrationTest/src/...` for shared database fixtures and end-to-end scenarios

## Build/test workflow
- Formatting: `./gradlew spotlessApply`
- Targeted unit test: `./gradlew :kontrol-db-core:test`
- Dialect integration tests: `./gradlew :kontrol-db-postgres:integrationTest`, `./gradlew :kontrol-db-sqlserver:integrationTest`
- Full verification: `./gradlew check` or `./gradlew build`
- PostgreSQL/SQL Server/Oracle integration tests are Docker-backed; HSQLDB runs locally without Docker.

## Editing rules
- Do not edit generated or build output (`**/build/**`, generated KSP files).
- Keep changes under source/build-script roots; module boundaries are important.
- If changing SQL behavior, validate with the affected dialect’s integration test.

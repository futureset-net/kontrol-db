# Copilot instructions for `kontrol-db`

## What this repo is
- `kontrol-db` is a Kotlin DSL-based database migration tool.
- Multi-module Gradle build:
  - `kontrol-db-core`: engine, DSL, migration orchestration.
  - `kontrol-db-hsqldb`, `kontrol-db-postgres`, `kontrol-db-sqlserver`, `kontrol-db-oracle`: dialect-specific extensions.
  - `integrationTest`: shared integration test fixtures and scenarios used by dialect modules.

## Architectural expectations
- Keep migration primitives and engine behavior in `kontrol-db-core`.
- Keep SQL dialect behavior inside the matching dialect module only.
- Dialect modules are wired via Koin module scanning (`@Module`, `@ComponentScan`) and KSP-generated modules.
- Refactorings are Kotlin classes extending `Refactoring` and declare `executionOrder`, `forward`, and `rollback` changes.
- Prefer extending existing DSL builders and `ModelChange` types over introducing ad-hoc SQL strings.

## Coding conventions
- Language/toolchain: Kotlin (JVM), Java toolchain from Gradle version catalog (currently Java 21).
- Formatting/linting is enforced by Spotless + ktlint.
- Follow existing code style in the repository:
  - Keep types and package names explicit and consistent with existing modules.
  - Use data classes for new `ModelChange` implementations (required by `Refactoring` init checks).
  - Prefer small composable functions/classes over large monolithic logic.

## Build and test expectations
- Always prefer targeted verification first, then broader verification.
- Useful commands:
  - `./gradlew spotlessApply`
  - `./gradlew check`
  - `./gradlew build`
  - `./gradlew :kontrol-db-core:test`
  - `./gradlew :kontrol-db-postgres:integrationTest`
  - `./gradlew :kontrol-db-sqlserver:integrationTest`
- Dialect integration tests are Docker-backed in local development (via `kontrol-db.docker` convention plugin).
- CI runs full `build` and checks integration coverage; avoid changes that silently skip tests.

## Safe editing rules
- Do not edit generated or ephemeral outputs:
  - `**/build/**`
  - generated KSP output under `build/generated/**`
  - docs/coverage artifacts produced by Dokka or JaCoCo
- Prefer changes under source/build script roots:
  - `*/src/main/**`, `*/src/test/**`, `*/src/integrationTest/**`
  - `build.gradle.kts`, `settings.gradle.kts`, `buildSrc/src/main/kotlin/*.gradle.kts`
- Keep module boundaries intact; do not move dialect-specific logic into core unless explicitly requested.
- Keep dependency changes minimal and aligned with `gradle/libs.versions.toml`.

## When implementing features or fixes
- Start by locating the owning module (`core` vs specific dialect).
- Add or update tests alongside behavior changes.
- For SQL behavior changes, validate at least one relevant integration test task for the affected dialect.
- Update docs (`README.md`/module docs) when behavior or public DSL changes.


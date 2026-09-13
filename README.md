# kontrol-db

A Kotlin DSL-based database migration engine for producing and applying database migrations across multiple SQL dialects.

Quickstart

```kotlin
val engine = KontrolDbEngineBuilder.dsl {
  dialect("hsqldb")
  dbSettings { jdbcUrl = "jdbc:hsqldb:mem:quickstart" }
}
engine.applySql()
```

Full documentation and examples are on the project site: https://futureset-net.github.io/kontrol-db/ (see the Quickstart landing page and the Examples index).

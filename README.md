# kontrol-db

A database migration tool.

Design driving points

1. Changes expressed in simple Kotlin classes
   2. So that we can use templating, type safety, autocompletion and DSL
   3. Classes are easier to manage than lots of resource files containing text
   4. Some tools allow files as input rather than classes. This can cause confusion with resource location.
   5. We can package and distribute as a typical Jar dependency.
   6. We can avoid XML,SQL and YAML
   7. We can unit test or verify the changes programmatically.
2. Abstracted away from SQL so
   3. We can create and express changes without knowledge of SQL
   4. The same changesets can be targeted towards multiple databases e.g. H2 and DB2
   5. We don't need to execute our changes in a database before allowing the tool to do it
3. Easy of extensibility
   4. New refactoring base classes can be created as templates for very complex refactorings we want to reuse

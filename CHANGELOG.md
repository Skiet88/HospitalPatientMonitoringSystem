# CHANGELOG

## 2026-05-10 - Assignment 11

### Added
- Added generic repository interface and entity repository interfaces under repositories/com/hpms/repositories.
- Added in-memory HashMap repository implementations under repositories/com/hpms/repositories/inmemory.
- Added repository storage abstraction with RepositoryFactory under factories/com/hpms/factories.
- Added future storage stub DatabasePatientRepository under repositories/com/hpms/repositories/database.
- Added repository CRUD tests under tests/com/hpms/repositories/RepositoryLayerTest.java.
- Added Assignment 11 issue/backlog checklist in ASSIGNMENT11_ISSUES.md.

### Updated
- Updated pom.xml to include repositories and factories as source directories.
- Updated README.md with Assignment 11 design justification and abstraction rationale.
- Updated CLASS_DIAGRAM.md with repository layer class diagram.

## 2026-05-03 - Assignment 10

### Added
- Implemented UML class diagram in Java under src/com/hpms/domain and src/com/hpms/service.
- Added all six creational patterns under creational_patterns/com/hpms/patterns:
  - Simple Factory
  - Factory Method
  - Abstract Factory
  - Builder
  - Prototype
  - Singleton
- Added JUnit 5 tests under tests/com/hpms for all patterns and selected domain behaviors.
- Added Maven project configuration with JaCoCo coverage plugin in pom.xml.

### Updated
- Updated README.md with language choice, design decisions, pattern rationale, and test/coverage steps.

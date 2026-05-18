# CHANGELOG

## 2026-05-18 - Assignment 12

### Added
- Added service layer for three core entities under services/com/hpms/services:
  - PatientService
  - UserService
  - AlertService
- Added service exceptions for business rule and not-found handling.
- Added REST API layer under api/com/hpms/api with controllers for patient, user, and alert workflows.
- Added API request DTOs and centralized exception handler.
- Added integration test for REST API flow under tests/com/hpms/api/ApiIntegrationTest.java.
- Added service unit tests under tests/com/hpms/services.
- Added OpenAPI specification file at docs/openapi.yaml.

### Updated
- Updated pom.xml with Spring Boot + SpringDoc dependencies and source folder registration for services/api.
- Updated README.md with Assignment 12 architecture, endpoints, docs, and run instructions.

## 2026-05-10 - Assignment 11

### Added
- Added generic repository interface and entity repository interfaces under repositories/com/hpms/repositories.
- Added in-memory HashMap repository implementations under repositories/com/hpms/repositories/inmemory.
- Added repository storage abstraction with RepositoryFactory under factories/com/hpms/factories.
- Added future storage stub DatabasePatientRepository under repositories/com/hpms/repositories/database.
- Added repository CRUD tests under tests/com/hpms/repositories/RepositoryLayerTest.java.

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

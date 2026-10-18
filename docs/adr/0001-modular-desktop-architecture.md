# 0001. Modular Desktop Architecture

**Status:** Accepted
**Date:** 2026-10-18

## Context

Distribution Workbench is a Kotlin/JVM desktop application for procurement, inventory, document processing, and analytics using fictional data and explicitly specified business rules.

Its workflows combine calculations, transactional persistence, background processing, document import and export, and interactive desktop screens. These responsibilities need clear boundaries and separate verification.

The project is at the initialization stage. This decision defines the intended structure; build configuration and application implementation are not available yet.

## Decision

Build one desktop application using four Gradle subprojects: domain, application, infrastructure, and desktop.

Use Eclipse Temurin JDK 25 LTS and Gradle with Kotlin DSL. Commit the Gradle Wrapper and pin verified build and dependency versions during bootstrap.

Keep one Git root and preserve the repository's source, test, specification, and architecture-document roles.

## Module Responsibilities

| Module | Responsibility |
| --- | --- |
| domain | Domain models, value objects, calculations, and business invariants |
| application | Use cases, repository and adapter interfaces, workflow coordination, and transaction-boundary contracts |
| infrastructure | PostgreSQL and Exposed persistence, spreadsheet and PDF processing, serialization, and filesystem adapters |
| desktop | JavaFX and FXML interface, presentation state, application startup, and dependency composition through Koin |

Organize code inside these modules by capability, such as catalogues, inventory, procurement, documents, and analytics.

## Dependencies

- domain has no project dependencies and uses the Kotlin standard library.
- application depends on domain.
- infrastructure depends on application and domain.
- desktop depends on application and connects infrastructure implementations during startup.

Keep the project dependency graph acyclic.

The desktop startup code is the composition root. Controllers and presentation models call application contracts rather than constructing persistence or document-processing implementations.

Because desktop includes infrastructure for startup composition, Gradle dependencies alone do not enforce this distinction within desktop packages. Verify that boundary through code review and appropriate architecture checks.

## Execution Boundaries

Domain rules remain independent of UI and persistence types. Keep JavaFX controls, Exposed entities, database transactions, and document-library objects outside domain contracts.

Application use cases define operation boundaries. Infrastructure implements persistence and transaction contracts without leaking Exposed types into application interfaces.

Run database access, document processing, and substantial calculations outside the JavaFX application thread. Background operations expose progress, support cancellation where applicable, and propagate failures to the caller.

Use independently specified business rules and fictional fixtures throughout the application and its tests.

## Repository Layout

```text
build.gradle.kts
settings.gradle.kts
gradlew
gradlew.bat
gradle/
    libs.versions.toml
    wrapper/
src/
    domain/
    application/
    infrastructure/
    desktop/
tests/
    domain/
    application/
    infrastructure/
    desktop/
specs/
docs/
    adr/
scripts/
```

Map each Gradle subproject to src/<module>. Each subproject owns its build.gradle.kts and Kotlin sources under kotlin/.

Configure test sources under tests/<module>/kotlin. Add test resources and additional test suites when their verification purpose is defined.

Place desktop FXML, CSS, and other application resources under src/desktop/resources. Add resources for other modules only when required.

Configure these source and resource locations explicitly in Gradle. Create directories when they contain meaningful files.

## Alternatives Considered

A single Gradle project would require fewer build files, but would provide weaker dependency separation between domain logic, application behavior, persistence, and presentation.

Separate Gradle projects for every business capability would add build configuration before those capability boundaries are established. Keep capabilities inside the four modules and revisit extraction when concrete dependencies justify it.

## Consequences

Domain and application behavior can be tested without starting JavaFX or connecting to PostgreSQL.

Persistence and document processing receive focused integration checks, while desktop checks cover presentation behavior and application composition.

The structure requires explicit subproject dependencies and source-set configuration. Maintain those mappings together with the documented repository layout.

## Verification

During implementation:

- Verify the configured JDK and Gradle versions through the Wrapper.
- Compile all four modules and inspect their dependency boundaries.
- Verify Kotlin, resource, and test discovery in the configured locations.
- Launch the desktop application and verify FXML and resource loading.
- Run relevant tests for implemented calculations, workflows, transactions, and failure paths.
- Document exact setup and verification commands after successful local execution.

## References

- [Project overview](../../README.md)
- [Working agreements](../../AGENTS.md)
- [Gradle multi-project builds](https://docs.gradle.org/current/userguide/multi_project_builds.html)
- [Kotlin Gradle configuration](https://kotlinlang.org/docs/gradle-configure-project.html)

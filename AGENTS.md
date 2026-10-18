# AGENTS.md

## Project

Distribution Workbench is a Kotlin desktop demonstration project for distribution workflow automation. Its planned scope includes procurement, inventory, document processing, and analytics.

Preserve meaningful engineering depth through integrated workflows, transactional operations, background processing, validation, and failure recovery.

## Project Skills

Read `.agents/skills/project-guide/SKILL.md` when entering the project or locating its requirements, structure, commands, and verification guidance.

Follow its `references/kb-index.md` to the sources relevant to the current task. Use native skill loading when available; otherwise read the files directly.

Keep shared working agreements here and detailed project knowledge with its owning documents. Loading a skill does not grant additional implementation, publication, or deployment authority.

## Technology Baseline

| Area | Technology | State |
| --- | --- | --- |
| Language and runtime | Kotlin/JVM, Eclipse Temurin JDK 25 LTS | Configured |
| Build | Gradle, Kotlin DSL, and Gradle Wrapper | Configured |
| Desktop interface | JavaFX and FXML | Configured |
| Dependency injection | Koin | Configured for desktop startup |
| Automated testing | JUnit Jupiter | Configured |
| Code style | ktlint | Configured |
| Database and persistence | PostgreSQL and Exposed | Planned |
| Background processing | Kotlin coroutines | Planned |
| Excel processing | Apache POI | Planned |
| PDF processing | Apache PDFBox | Planned |
| Serialization | Kotlinx Serialization and Jackson | Planned |
| Test doubles | MockK | Planned |

Use pinned versions from `gradle/libs.versions.toml` and `gradle/wrapper/gradle-wrapper.properties`. The toolchain resolver plugin is pinned in `settings.gradle.kts`. Verify compatibility and local checks before changing these inputs.

Follow [ADR 0001: Modular Desktop Architecture](docs/adr/0001-modular-desktop-architecture.md) for module boundaries and source and test mappings. Read it before changing these decisions.

Add planned dependencies when an implemented workflow gives them a defined responsibility.

## Setup and Commands

Run these commands from the repository root:

| Command | Purpose |
| --- | --- |
| `./gradlew build` | Assemble modules and run verification |
| `./gradlew check` | Run code-style checks and tests |
| `./gradlew ktlintFormat` | Format Kotlin sources and Gradle Kotlin scripts |
| `./gradlew :desktop:run` | Launch the desktop application |
| `./gradlew projects` | Inspect the configured module graph |
| `./gradlew javaToolchains` | Inspect available and provisioned JDKs |

A JDK 17 or newer starts Gradle. The configured toolchain selects and can provision Eclipse Temurin JDK 25 for compilation, tests, and application execution.

Complete formatting in a separate invocation before verification. UI tests require a graphical desktop session and create their own temporary window.

Run development tools and verification locally. Do not launch, rerun, or enable GitHub Actions execution.

## Repository Structure

- `src/<module>/build.gradle.kts`: module build configuration.
- `src/<module>/kotlin/`: module Kotlin sources.
- `src/<module>/resources/`: module resources when needed.
- `tests/<module>/kotlin/`: module tests.
- `tests/<module>/resources/`: test resources when needed.
- `gradle/`: Wrapper metadata and the dependency version catalogue.
- `scripts/`: project utilities when needed.
- `specs/`: feature requirements, plans, and tasks.
- `docs/adr/`: architecture decisions.
- `.agents/skills/`: project-specific skills.
- `.memory/`: local session continuity.
- `private/`: local internal materials.

Keep one Git root. Create directories when they have meaningful content.

## Implementation and Verification

Keep interface handling, application behavior, and persistence responsibilities separate.

Do not block the JavaFX application thread with database access, document processing, or long calculations. Background operations must handle cancellation and propagate failures.

Verify calculations, input validation, transactional behavior, and failure recovery as their workflows are implemented.

Run checks relevant to the change and report their actual results. Distinguish a configured module from an implemented workflow and tests that executed from tasks reporting NO-SOURCE. State explicitly when a required check cannot run.

## Data and Public Independence

Use fictional data and explicitly defined business rules.

The public repository must contain everything required for documented development, builds, tests, and execution. Verify these workflows from a clean clone as they become available.

Public documentation describes this project independently. It must not require private research, internal tools, personal filesystem paths, or external project context.

Do not include confidential commercial records, personal data, credentials, or internal working materials in published files.

Keep `.memory/` and `private/` outside Git. Review the actual outgoing files before publication.

## Working Agreements

Communicate with the owner in Russian. Write project documentation, code identifiers, comments, and commit messages in English.

During initialization, agree on decisions step by step before applying them. Broader autonomous or delegated work requires the owner's explicit instruction.

Present the full proposed text of Markdown files for owner approval before creating or changing them.

Keep requirements in `specs/` and architecture decisions in `docs/adr/`. Reference the relevant documents when needed rather than duplicating their contents here.

## Git Workflow

Use Conventional Commits without AI attribution.

Changes to `main` require pull requests. The owner performs the final merge. Force pushes and branch deletion are prohibited.

Branch protection must be enforced on GitHub; this file alone does not provide protection.

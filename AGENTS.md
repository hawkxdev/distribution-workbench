# AGENTS.md

## Project

Distribution Workbench is a Kotlin desktop demonstration project for distribution workflow automation. Its planned scope includes procurement, inventory, document processing, and analytics.

Preserve meaningful engineering depth through integrated workflows, transactional operations, background processing, validation, and failure recovery.

## Technology Baseline

The planned stack is:

| Area | Technology |
| --- | --- |
| Language and runtime | Kotlin/JVM |
| Desktop interface | JavaFX and FXML |
| Database | PostgreSQL |
| Persistence | Exposed |
| Dependency injection | Koin |
| Background processing | Kotlin coroutines |
| Excel processing | Apache POI |
| PDF processing | Apache PDFBox |
| Serialization | Kotlinx Serialization and Jackson |
| Automated testing | JUnit Jupiter and MockK |

Build tooling, the JDK version, dependency versions, and module boundaries remain open decisions. Resolve them before creating the application skeleton.

Use each dependency for a defined responsibility. Do not add frameworks or services solely to increase the technology count.

## Setup and Commands

Application setup, build, test, and quality commands are not configured yet.

Add exact commands here after the build tooling is selected and the commands are verified locally. Do not present proposed commands as working instructions.

Run development tools and verification locally. Do not launch, rerun, or enable GitHub Actions execution.

## Repository Structure

- `src/`: application sources.
- `tests/`: automated tests.
- `scripts/`: project utilities.
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

Run checks relevant to the change and report their actual results. State explicitly when a required check cannot run.

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

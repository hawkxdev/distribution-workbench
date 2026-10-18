# Distribution Workbench

A Kotlin/JVM desktop application project for procurement, inventory, document processing, and analytics in a fictional distribution business.

## Project Status

The initial desktop foundation is implemented: four Gradle modules, a runnable JavaFX/FXML window, Koin configuration, and automated bootstrap checks.

Procurement, inventory, document processing, and analytics workflows are planned. Detailed requirements and business rules are defined before their implementation.

## Planned Workflows

- Import fictional supplier catalogues and validate incoming records.
- Work with inventory, reservations, and procurement requirements.
- Compare supplier offers and prepare purchase orders.
- Process spreadsheet and PDF documents.
- Calculate operational summaries and export reports.

## Technology Stack

| Area | Technology | Status |
| --- | --- | --- |
| Language | [Kotlin/JVM 2.4.21](https://kotlinlang.org/docs/jvm-get-started.html) | Configured |
| Application JDK | [Eclipse Temurin 25 LTS](https://adoptium.net/support/) | Configured through Gradle toolchains |
| Build | [Gradle 9.7.1](https://docs.gradle.org/current/userguide/gradle_wrapper.html), Kotlin DSL, and Gradle Wrapper | Configured |
| Desktop interface | [JavaFX 27](https://openjfx.io/) and FXML | Configured |
| Dependency injection | [Koin 4.2.2](https://insert-koin.io/docs/) | Configured for desktop startup |
| Automated testing | [JUnit Jupiter 6.1.3](https://docs.junit.org/current/user-guide/) | Configured |
| Code style | [ktlint Gradle plugin 14.2.0](https://github.com/JLLeitschuh/ktlint-gradle) | Configured |
| Database | [PostgreSQL](https://www.postgresql.org/docs/) | Planned |
| Persistence | [Exposed](https://www.jetbrains.com/help/exposed/home.html) | Planned |
| Background processing | [Kotlin coroutines](https://kotlinlang.org/docs/coroutines-overview.html) | Planned |
| Excel processing | [Apache POI](https://poi.apache.org/) | Planned |
| PDF processing | [Apache PDFBox](https://pdfbox.apache.org/) | Planned |
| Serialization | [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) and [Jackson](https://github.com/FasterXML/jackson) | Planned |
| Test doubles | [MockK](https://mockk.io/) | Planned |

Dependency versions are pinned in the [version catalogue](gradle/libs.versions.toml). The Gradle distribution and its checksum are pinned in [Wrapper properties](gradle/wrapper/gradle-wrapper.properties).

## Architecture

The architecture is documented in [ADR 0001: Modular Desktop Architecture](docs/adr/0001-modular-desktop-architecture.md).

The Gradle modules are `domain`, `application`, `infrastructure`, and `desktop`, separating business rules, use cases, adapters, and the desktop interface. The first three currently establish build boundaries; their business implementations are added with feature work.

## Development Setup

Prerequisites:

- JDK 17 or newer to start Gradle.
- Network access for the first dependency and toolchain downloads.
- A graphical desktop session for running the application and UI tests.

Gradle selects and, when necessary, downloads Eclipse Temurin JDK 25 for compilation, tests, and application execution. A global switch to Java 25 is not required.

From a terminal on macOS:

```bash
git clone https://github.com/hawkxdev/distribution-workbench.git
cd distribution-workbench
./gradlew build
./gradlew :desktop:run
```

The application currently opens the initial workspace window. Database provisioning is introduced with persistence workflows.

## Verification

Run commands from the repository root:

| Command | Purpose |
| --- | --- |
| `./gradlew build` | Assemble modules and run verification |
| `./gradlew check` | Run code-style checks and tests |
| `./gradlew ktlintFormat` | Format Kotlin sources and Gradle Kotlin scripts |
| `./gradlew :desktop:run` | Open the desktop application |

Complete formatting before starting a separate verification command.

Tests cover desktop configuration validation and window composition, including FXML, CSS, and named JavaFX module loading. The UI test briefly opens and closes its own window.

The foundation is verified on macOS with Apple Silicon. Windows and Linux execution and standalone application packages remain unverified.

## Engineering Focus

Feature development focuses on transactional persistence, responsive desktop interactions, cancellable background operations, repeatable imports, and actionable failure diagnostics.

## Demonstration Data

Sample catalogues, supplier offers, inventory records, and documents use fictional data. Business rules are specified explicitly and verified through tests as their workflows are implemented.

## Project Guidance

Contributor and agent working agreements are documented in [AGENTS.md](AGENTS.md).

## License

License selection is pending.

# Distribution Workbench

A Kotlin/JVM desktop application project for procurement, inventory, document processing, and analytics in a fictional distribution business.

## Project Status

The project is at the initialization stage. Application implementation has not started, and a runnable build is not available yet.

Product requirements, architecture, build tooling, and dependency versions are being defined.

## Planned Workflows

- Import fictional supplier catalogues and validate incoming records.
- Work with inventory, reservations, and procurement requirements.
- Compare supplier offers and prepare purchase orders.
- Process spreadsheet and PDF documents.
- Calculate operational summaries and export reports.

These workflows describe the intended direction. Detailed requirements and acceptance criteria are established before implementation.

## Planned Technology Stack

| Area | Technology |
| --- | --- |
| Language and runtime | [Kotlin/JVM](https://kotlinlang.org/docs/jvm-get-started.html) |
| Desktop interface | [JavaFX](https://openjfx.io/) and FXML |
| Database | [PostgreSQL](https://www.postgresql.org/docs/) |
| Persistence | [Exposed](https://www.jetbrains.com/help/exposed/home.html) |
| Dependency injection | [Koin](https://insert-koin.io/docs/) |
| Background processing | [Kotlin coroutines](https://kotlinlang.org/docs/coroutines-overview.html) |
| Excel processing | [Apache POI](https://poi.apache.org/) |
| PDF processing | [Apache PDFBox](https://pdfbox.apache.org/) |
| Serialization | [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) and [Jackson](https://github.com/FasterXML/jackson) |
| Automated testing | [JUnit Jupiter](https://junit.org/) and [MockK](https://mockk.io/) |

This is the planned technology baseline. Exact versions and responsibilities are finalized during architecture and build setup.

## Engineering Focus

Development focuses on connected workflows and explicit business invariants:

- Transactional persistence and consistent updates.
- Background operations with progress, cancellation, and error handling.
- Responsive desktop interactions.
- Repeatable imports with actionable validation diagnostics.
- Tests for calculations, boundary conditions, and failure recovery.

## Demonstration Data

Sample catalogues, supplier offers, inventory records, and documents use fictional data. Business rules are specified explicitly and verified through tests as their workflows are implemented.

## Development Setup

Installation, database setup, build, and test instructions are added when the corresponding tooling is configured and verified.

Contributor and agent working agreements are documented in [AGENTS.md](AGENTS.md).

## License

License selection is pending.

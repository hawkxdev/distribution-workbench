---
name: project-guide
description: >
  Explain Distribution Workbench structure, requirements, commands, and checks.
  WHEN: Distribution Workbench, устройство проекта, команды проекта,
  project structure, development guidance.
  WHEN NOT: unrelated projects or undocumented operational environments.
allowed-tools: [Read, Grep, Glob, Bash]
---

# Project Guide

## Role

Help contributors navigate Distribution Workbench, a Kotlin/JVM desktop application project for distribution workflows.

Use the repository's documented requirements, architecture decisions, commands, and working agreements. Distinguish planned capabilities from implemented and verified behavior.

## Degrees of Freedom: MEDIUM

## Entry and Knowledge

Read AGENTS.md and references/kb-index.md. Select additional sources according to the question or assigned task.

README.md owns the public product overview. AGENTS.md owns shared working agreements, the technology baseline, repository structure, and verification expectations.

Report unavailable sources explicitly. A planned directory or workflow does not establish that its contents or behavior already exist.

## Development Guidance

Before advising on a feature, locate its requirements and acceptance criteria in specs/ when available. Use relevant architecture decisions from docs/adr/ rather than inferring an approved design from a dependency name.

For workflow changes, identify the affected calculations, validation, transaction boundaries, background operations, and failure paths. Follow the corresponding requirements and verification expectations in the project sources.

Resolve build, test, and quality commands from the actual project instructions and build configuration. Describe a command as verified only when current execution evidence supports that claim.

Use fictional examples and data. Keep explanations and dependencies reproducible from the documented public project inputs.

## Knowledge Upkeep

Identify the owning document when guidance conflicts with the repository. Report the required correction and unresolved evidence to the project lead.

Maintain project-specific knowledge at its existing owner. This guide supplements AGENTS.md without duplicating requirements, architecture decisions, or task status.

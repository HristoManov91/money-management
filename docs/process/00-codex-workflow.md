# Planning -> Implementation -> Independent Review

This workflow is a draft specification of the owner's preferred delivery process and applies to future implementation issues only once accepted.

## Stages
1. Interview and capture the problem in Bulgarian; separate user-confirmed behavior from proposals and unresolved choices.
2. Revise version-controlled specs, ADRs, definitions and sample scenarios using a **Draft PR**.
3. Independently review the discovery documents; only after approval create/activate implementation epics.
4. Define one implementation Issue at a time with: problem, scope, out of scope, dependencies, business rules, domain constraints, API/DB/UI impacts, migration/data compatibility, error behavior, numbered acceptance criteria, tests and observable success conditions.
5. Provide Codex with a **single self-contained copyable prompt** in English per Issue, including exact scope and no unrelated refactor.
6. Codex implements using one **Draft PR**; it must not mark Ready, auto-merge, close issues or claim tests that were not run.
7. Implementation result must show changed files, final commit SHA, reproducible tests and test counts, CI checks at **that SHA**, risks, deviations, and remaining work.
8. ChatGPT independently compares the full changed PR against current main and Issue/ADR contract, including security, concurrency, finance rounding, privacy, accessibility and regressions.
9. On review findings, write a single copyable fix prompt, run checks again, and re-review. User explicitly approves Ready/merge/closure.

## Quality gates (adapt after project bootstrap)
- Backend unit/integration/security tests; real PostgreSQL behavior through Testcontainers where required.
- Frontend lint/format/typecheck/unit/build and Playwright flows for affected features.
- Database migration forward-compatibility and transactional correctness.
- Synthetic sample receipts and error/missing-field cases.
- Authorization tests: cross-household isolation, upload/read/delete/export, role boundaries.
- Money invariants: total/line allocation, rounding, refunds, transfers, duplicate ingestion.
- No credentials, private receipts, source financial records or PII in the public repo.
- CI efficiency: cache, change-scoped jobs, cancel superseded runs, avoid wasteful scheduled jobs.

## Document ownership
Product spec answers **what / why**; ADR answers **technical choice / tradeoff**; AGENTS.md answers **repository rules**; GitHub Issue answers **single deliverable**; Codex prompt answers **next bounded action**; CI and independent review answer **whether correct**.

## Current stop condition
**Do not implement any product functionality yet.** Complete the stage-by-stage discovery and sign off the MVP and deployment model first.

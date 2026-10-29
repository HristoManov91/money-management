# Technical direction — non-binding draft (2026-10-29)

This document proposes a modern **modular monolith**, not a microservice fleet. Final choices, patch versions, authentication and deployment depend on discovery/ADRs.

## Established stack and current candidate
- Backend: **Java 25 LTS**, **Spring Boot 4.1.x** (4.1.2 latest stable checked on 2026-10-29), Spring Security (WebAuthn/passkey support), Maven, Jakarta validation, structured logging and generated OpenAPI contract.
- Persistence: **PostgreSQL**, versioned forward-only **Flyway** migrations, strict tenant/household authorization at service and query boundaries, transaction/locking semantics specified for concurrent edits.
- Frontend: **Nuxt 4 + Vue 3 + TypeScript**, **pnpm**, PrimeVue/accessible design system; reuse familiar build approach from the owner's maintained Vue/Nuxt project. Responsive browser UI first, evaluate PWA (offline and native wrappers are decisions, not promises).
- Tests: JUnit 5, Testcontainers/PostgreSQL, Spring integration/security tests, Vitest, Playwright E2E; synthetic receipts and privacy-safe fixtures.
- API: versioned HTTP/JSON REST with OpenAPI; server-owned monetary calculations; idempotent mutation/import operations and explicit error contracts.
- Delivery: Docker Compose for local development, CI PR checks scoped to changed modules with caching, no continuous paid background jobs by default.

Official links:
- https://docs.spring.io/spring-boot/
- https://docs.spring.io/spring-security/reference/servlet/authentication/passkeys.html
- https://nuxt.com/docs

## Security baseline
- No custom password database unless a clear need emerges; assess **passkeys/WebAuthn** plus a safe onboarding and lost-device recovery flow. Google/Apple/email magic link are possibilities, not approved alternatives.
- Household-scoped authorization on **all** objects, imports, downloads, queries and aggregates. Prevent IDOR, cross-workspace leaks and privilege escalation.
- CSRF protection appropriate to the browser/session strategy; hardened cookies, rate limiting, TLS and session invalidation.
- Cryptographically strong invite/recovery tokens, scoped expiration, audit and revocation.
- Receipts may expose names, locations, payment identifiers and personal details. Separate secure object storage with short-lived access; validate file content/size and block unsafe uploads.
- If any external OCR/AI provider is used, require explicit provider/privacy/retention decisions, avoid logging personal document text, and offer fallback manual entry.
- Backups, restore drills and export/delete capabilities; no assumptions that 'cloud' implies safe backups.
- Public GitHub repository contains **only synthetic test data**. Never put production environment or financial content into issues, PRs, fixtures, screenshots, or CI logs.

## Financial correctness baseline
- Decimal money arithmetic and currency-specific rounding; stable type definitions.
- Separate expense, income, transfer and (if approved) refund behaviors.
- Receipt item amounts are not an independent extra expense in addition to their parent transaction.
- No generated financial entry from OCR without an explicit human approval action.
- Include version/concurrency control for edits, audit metadata, and idempotency for upload/import.

## Architecture decisions still required
1. Server and domain boundaries (household/account/transaction/receipt/catalog/report) and optional Spring Modulith conventions.
2. Hosting and operational cost ceilings; object storage/provider; encrypted backups.
3. Email/passkey enrollment, social identity support, safe recovery, mobile browser behavior.
4. Offline local drafts, outbox/sync/conflict rules, and PWA service worker boundaries.
5. Document processing pipeline/provider/model, queue architecture, retries, compensation and deletion.
6. Query/report performance and export generation strategy for multi-year item-level history.
7. Whether/how the public 2023 code is retained, archived, or superseded.
8. Exact dependency patch versions to be pinned **at implementation start**, with compatibility and security checks.

## Deliberate exclusions
No Kafka, distributed microservices, elaborate event sourcing, Kubernetes, or complex subscriptions solely for perceived modernity. Add infrastructure only when a confirmed workflow or measurable constraint demands it.

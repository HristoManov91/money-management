# Decisions and open questions

**Only explicit user confirmation can change a proposed decision to Accepted.**
This is public documentation; never write real family income, personal identifiers, receipts, detailed balances or bank details here.

| ID | Topic | Status | Candidate / question |
| --- | --- | --- | --- |
| D-001 | Repository | ACCEPTED | Create a **new** public repository for the new system; legacy `money-management` is reference-only and must not be deleted before discovery documents and Issues have been safely recreated and verified in the new repo. New repository creation is still pending. |
| D-002 | Platform | CANDIDATE | **Android and iPhone support are confirmed needs.** Proposed: installable Nuxt/Vue PWA first, optionally package with Capacitor if App Store/Google Play distribution is important. User has not approved the packaging choice yet. |
| D-003 | Hosting | OPEN | Private/self-hosted vs low-cost cloud vs mixed |
| D-004 | Family data | OPEN | Family sharing is required; precise sharing/ownership boundary for user-created financial accounts and separate vs joint ledgers remains open. |
| D-005 | Attribution | ACCEPTED (partial) | Record **who paid** for the expense, not a separate beneficiary field. Payer and source financial account remain distinct fields; permissions and any shared/other payer semantics remain open. |
| D-006 | Accounts | ACCEPTED (partial) | User can create **arbitrarily named financial accounts**; choose account per transaction; income increases and expense decreases the account balance. Cards, cash and bank accounts are all valid names. Opening balances, credit-card liabilities, account visibility and transfers remain open. |
| D-007 | Budget source | OPEN | Fixed monthly limit, income-based limit, or both |
| D-008 | Item categories | OPEN | Required on each line, defaults from header, override rules, mixed categories |
| D-009 | Categorization | OPEN | Hierarchy depth, user custom icons, product matching and aliases |
| D-010 | Receipts | OPEN | Built-in upload only vs additionally ChatGPT conversation integration |
| D-011 | OCR/AI | OPEN | Local/hosted/cloud processing, validation, cost constraints, fallbacks |
| D-012 | Bluecoins migration | ACCEPTED (partial) | Import long-standing transaction history from Bluecoins without losing existing information. Newly introduced fields may be empty for older transactions. Format/verification/deduplication details still open. |
| D-013 | Authentication | OPEN | Passkeys + recovery mechanism; invitation and multi-device registration |
| D-014 | Offline | OPEN | Full offline entry & sync or network-required initially |
| D-015 | Currency | OPEN | EUR default, legacy BGN and other currencies, exchange-rate rules |
| D-016 | Exports | CONFIRMED NEED | PDF, native Excel XLSX and CSV; exact datasets and formats to define |
| D-017 | User interface | CONFIRMED NEED | Mobile and browser UI; icons/images; design system to define |
| D-018 | Backend/frontend stack | CANDIDATE | Java 25/Spring Boot 4.1.x + Nuxt 4/Vue 3; verify at implementation kickoff |
| D-019 | Delivery workflow | CONFIRMED NEED | Stage-by-stage interviews -> approved specs -> epics/issues -> Codex Draft PR -> review, with the detailed gates to be agreed. |

| D-020 | Free-text transaction note | ACCEPTED | In addition to transaction name, every transaction supports an optional free-text description/note. | 
| D-021 | Historical itemization | ACCEPTED | Migrated Bluecoins transactions need not contain the new item fields; preserve history as recorded, never fabricate product or quantity data. | 

## Decision record format
When resolved, add a dated entry with: decision ID, accepted choice, alternatives considered, key rationale, consequences, owner approval date and links to updated specifications. Avoid sensitive household specifics in this public repository.

## State definitions
- CONFIRMED NEED = user directly requested capability, but detailed implementation may remain unspecified.
- CANDIDATE = design proposal, subject to discussion.
- OPEN = blocked on user response/architecture investigation.
- ACCEPTED = explicit choice approved by the user; document why and what it replaces.
- SUPERSEDED = replaced by a linked newer decision.

## Change management
If a decision changes, update the relevant source documents and affected epic/issues in the same reviewed PR. Do not silently alter accepted financial invariants or user-visible data semantics.

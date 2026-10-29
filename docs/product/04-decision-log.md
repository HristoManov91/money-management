# Decisions and open questions

**Only explicit user confirmation can change a proposed decision to Accepted.**
This is public documentation; never write real family income, personal identifiers, receipts, detailed balances or bank details here.

| ID | Topic | Status | Candidate / question |
| --- | --- | --- | --- |
| D-001 | Repository | OPEN | Reuse 2022–23 public `money-management` with an isolated restart vs new public repo |
| D-002 | Platform | OPEN | Responsive browser + installable PWA first; native wrapper later if necessary |
| D-003 | Hosting | OPEN | Private/self-hosted vs low-cost cloud vs mixed |
| D-004 | Family data | OPEN | Shared ledger only vs shared + personal ledgers, and how members are authorized |
| D-005 | Attribution | OPEN | Payer vs creator vs allocated-for-person vs account; 'shared/other' behavior |
| D-006 | Accounts | OPEN | Actual card/cash/bank balances and transfers vs expense tracking only |
| D-007 | Budget source | OPEN | Fixed monthly limit, income-based limit, or both |
| D-008 | Item categories | OPEN | Required on each line, defaults from header, override rules, mixed categories |
| D-009 | Categorization | OPEN | Hierarchy depth, user custom icons, product matching and aliases |
| D-010 | Receipts | OPEN | Built-in upload only vs additionally ChatGPT conversation integration |
| D-011 | OCR/AI | OPEN | Local/hosted/cloud processing, validation, cost constraints, fallbacks |
| D-012 | Bluecoins migration | OPEN | Historic export/import needed? Which date range, account mapping and backup? |
| D-013 | Authentication | OPEN | Passkeys + recovery mechanism; invitation and multi-device registration |
| D-014 | Offline | OPEN | Full offline entry & sync or network-required initially |
| D-015 | Currency | OPEN | EUR default, legacy BGN and other currencies, exchange-rate rules |
| D-016 | Exports | CONFIRMED NEED | PDF, native Excel XLSX and CSV; exact datasets and formats to define |
| D-017 | User interface | CONFIRMED NEED | Mobile and browser UI; icons/images; design system to define |
| D-018 | Backend/frontend stack | CANDIDATE | Java 25/Spring Boot 4.1.x + Nuxt 4/Vue 3; verify at implementation kickoff |
| D-019 | Delivery workflow | CANDIDATE | Stage-by-stage interviews -> specs -> epics/issues -> Codex Draft PR -> review |

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

# Staged interviews and requirements roadmap

Parent: [#6](https://github.com/HristoManov91/money-management/issues/6). This is the **single source of truth for discovery order**, not a Codex implementation prompt.

## Phase A — Product discovery (current)
| Stage | Topic | Deliverable |
| --- | --- | --- |
| A0 | Repository choice, current usage, household scope, desired hosting and mobile platforms | Vision and constraints confirmed |
| A1 | Family members, roles, ownership vs payer vs beneficiary, accounts and transfers | Access matrix + financial account semantics |
| A2 | Transaction header, item rows, split lines, returns, promotions, VAT, currencies | Transaction specification, examples and invariants |
| A3 | Categories, subcategories, product name/alias catalog, quantities and merchant | Classification and catalog rules |
| A4 | Receipt photo/PDF import, recognition and corrections, asynchronous jobs, duplicate protection | Extraction/review state machine and sample fixtures |
| A5 | Incomes, global budget, category/member caps, periods and carryover | Budget calculation rules + edge-case scenarios |
| A6 | Search, reports, comparisons, product trends and PDF/XLSX/CSV | Reporting catalog and export contract |
| A7 | Web/mobile UX, offline and sync, passkeys/recovery, privacy, backups | UX journeys and security assumptions |
| A8 | Bluecoins migration, duplicates and historical correctness | Import mapping with preview, reconciliation |
| A9 | Architecture/stack/deployment and release strategy | ADRs, nonfunctional requirements, MVP boundary |

### First interview (A0)
1. Reuse the existing public money-management repository as a clean restart, or use a new repository?
2. What does 'one account for both partners' mean in practice: a shared household ledger only, or separate personal ledgers plus shared ledger?
3. Are bank/cash/payment accounts and balances important, or is the primary goal expense/income tracking and a family spending limit?
4. Is mobile best delivered by a responsive installable PWA initially, or do native Android/iOS apps have non-negotiable features?
5. Is the system intended to run on private infrastructure, cloud hosting, or is this undecided?
6. Do you want to migrate full transaction history from Bluecoins?
7. Should document-to-expense creation live in the app itself, inside ChatGPT conversation, or eventually both?

Questions are illustrative. Ask them in small groups in Bulgarian, capture decisions with confirmation, and do not invent answers.

## Phase B — Backlog architecture
- Create a small set of **domain epics** after their spec has been agreed: Identity & Households; Accounts & Transactions; Taxonomy & Product Catalog; Receipt Intake/AI; Budgets & Recurring; Reporting & Exports; Bluecoins Import; Mobile/UX; Quality/Deployment.
- Each epic links to canonical specs and lists explicit cross-epic dependencies and out-of-scope items.
- Implementation Issues must be vertical, independently testable slices: problem, business rules, API/schema changes, UI scenarios, acceptance criteria, errors and limits, tests, migration, observability, security, dependencies.
- Track MVP / Later / Won't implement. Do not generate false precision (story points/dates) before requirements.

## Phase C — Implementation and review
- Start from an approved baseline + CI/security checks + local test environment.
- Codex takes **one implementation Issue**, creates **one Draft PR**, leaves it Draft and leaves Issue OPEN.
- Validate tests and CI at exact final head SHA; independent ChatGPT/user reviews the full diff and source requirements.
- Fix prompts remain copyable single blocks; merge only after explicit approval. No auto-merge.

## Discovery exit criteria
- All MVP screens and user workflows have signed-off examples.
- Domain entities and financial invariants are explicit (including refunds and item-total rounding).
- AI handling, verification states and data confidentiality are explicit.
- Hosting, passwordless sign-in, account recovery, family permissions and backup tested at design level.
- Codex implementation backlog is ordered; no blocking product questions are left inside coding tasks.

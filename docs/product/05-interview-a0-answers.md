# Discovery A0 — answers captured on 2026-10-29

> **This is a temporary planning record in the legacy public repo.** Once the new repo exists, recreate and verify this document and related Issues there, then retire the old planning artifacts. No old code should be copied by default. Do not delete the old repo until successful transfer is verified.

## Confirmed requirements / decisions from user

1. **Repository (accepted):** Start a completely new application/repository. The 2022–23 money-management code does not have to be kept. Deletion of old repo may be considered **later**, after safe migration; none authorized as part of the current change.
2. **User-defined accounts:** The user creates as many accounts as desired and assigns arbitrary display names; accounts may represent cash, cards, credit cards, named persons, or any other useful classification. A predefined mandatory naming scheme is **not** required.
3. **Money flows:** Record incomes and expenses against a chosen account. An expense reduces the selected account's balance; income increases it. User chooses the source/destination account on transaction entry.
4. **Payer and notes:** Keep a field indicating **who paid**, but no separate field for **who the purchase was for**. The transaction includes a name and a separate optional free-text description.
5. **Preserve familiar account features:** Do not remove the ability to have distinct bank/cash/card-like accounts. Clarify transfer/debt accounting separately before finalizing the schema.
6. **Migration:** Import substantial existing Bluecoins transaction history; missing new product-level fields in historical transactions are allowed. Preserve original categories, transaction names, dates, account assignments, notes and amounts insofar as exports permit.
7. **Platforms:** Android and iPhone are both in use. User prefers an installable mobile application. PWA versus app stores/native packaging remains a decision after explanation.

## Candidate user workflows, not final design

### Create an account
User chooses an arbitrary name (e.g. 'Household cash', 'Card A', 'Card B'), optional account kind for proper credit-card/debt semantics, currency and initial balance. Initial-balance semantics are **not yet agreed**.

### New transaction
Select expense or income; enter amount, name, required category and optional subcategory; select account; choose payer for expense; optionally add a free-text description, store and items. Preserve fast entry with sensible defaults.

### Financial invariants (to review)
- Expense posted to an asset-like account subtracts the amount.
- Income posted to an asset-like account adds the amount.
- A movement between owned accounts should update both balances and **not** count as new spending or income.
- Editing or deleting a transaction should consistently update account balances and all reports.
- Credit cards have liability semantics distinct from ordinary deposit accounts; do not invent a single sign convention before the user's preference is known.

## Open questions for next interview (A1 — Accounts)
- Do accounts have a starting balance that is not categorized as income?
- Should transferring funds between two accounts be an explicit transfer operation?
- Does a credit-card account track outstanding debt, available credit, or just an arbitrary signed running balance?
- Are user-created accounts visible to every household member or can they be private?
- Is payer an independent field (default current user), separate from the financial source account?
- Should account deletions be restricted/archived if transactions exist?

## Bluecoins source and migration cautions
Official sources:
- https://www.bluecoinsapp.com/guide/transactions/
- https://www.bluecoinsapp.com/import-guide/
- https://www.bluecoinsapp.com/guide/import-export/

Bluecoins transaction CSV fields may support transaction name/payee, date, amount, parent category, category, account/account type, notes, labels, status, split, currency/exchange rate. Exact **export** fields and sample need to be verified from actual user-generated export (shared privately, not uploaded to public GitHub). Advanced fields such as product gram weight or canonical product are not guaranteed; leave them absent rather than synthesize.

## Next step
Complete the account and household ownership interview. Only afterward update schema, architecture ADR, epics and Codex task breakdown.

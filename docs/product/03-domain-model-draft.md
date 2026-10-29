# Domain model v0.1 — **HYPOTHESES, NOT DECISIONS**

The purpose of this document is to expose hidden edge cases for staged user interviews. No table structure or API contract is approved.

## Candidate concepts

| Concept | Candidate purpose | Open decisions |
| --- | --- | --- |
| User | Separate passwordless identity and profile | Email/passkeys/federated sign-in, account recovery |
| Household / Workspace | Security and data boundary for the family ledger | One or multiple workspaces? invites? |
| Membership | A user with role in a household | owner/admin/member/viewer? who may edit others? |
| FinancialAccount | Where money moves (cash, card, bank, liability) | Is balance/reconciliation in MVP? |
| Transaction | Financial event: expense, income or transfer, with timestamp, amount, currency, name and a required category | Refund representation, transfer fee, timezone |
| TransactionLine | One purchased item or an unallocated/synthetic adjustment | Required per-line category or inherited default? |
| Category | Household-defined tree; main category and optional subcategory in user-facing model | Depth, rename/move/history, archived categories |
| Product | Canonical optional catalog identity with receipt-specific original names and aliases | When to merge or distinguish products? |
| Merchant | Store/vendor as seen on receipt, possibly canonicalized | Store branch vs merchant chain, location/privacy |
| HouseholdAttribution | 'Mine / partner's / shared / other' or percent allocation | Separate who paid, whose budget, who entered |
| Budget | A period-and-scope constraint on categories/household/members | Monthly ceiling vs category caps, changes mid-period |
| Receipt / SourceDocument | Immutable source file metadata with household ACL and review state | Retention, encrypted storage, deletion |
| ReceiptExtraction | Structured machine proposal with confidence/provenance at line/field level | OCR/provider, retries, low-confidence behavior |
| ImportJob | External-ledger CSV import with preview, idempotency key, report | Rollback, duplicates, migration mapping |
| AuditEvent | Who made material edits and when | Visibility, retention and user privacy |

## Example (synthetic data only)

**Transaction:** Grocery purchase; merchant: Example Market; date: 2026-10-01; paid by: Member A; total: EUR 12.30; transaction category: Food.

| Item | Quantity/unit | Unit price | Line total | Main category | Subcategory |
| --- | --- | --- | --- | --- | --- |
| Apples | 1.5 kg | EUR 2.00/kg | EUR 3.00 | Food | Fruit & vegetables |
| Milk | 2 pcs | EUR 1.65/pc | EUR 3.30 | Food | Dairy |
| Soap | 2 pcs | EUR 3.00/pc | EUR 6.00 | Household | Cleaning |

This illustrates a critical unanswered rule: the transaction has a required 'Food' main category even though one receipt item belongs to 'Household'. Budget totals **must use item-level category allocations** where available rather than double-counting the header.

## Constraints requiring product sign-off
1. For an expense with fully itemized lines, **sum of final line values plus explicitly modeled adjustments must equal the paid amount in transaction currency**, with defined rounding and reconciliation behavior.
2. A simple manual transaction may have zero explicit item rows, but still needs a reportable category and amount (or a generated aggregate line internally).
3. A source receipt may be used to produce a **Draft**; nothing from uncertain AI extraction enters final ledgers until reviewed/confirmed.
4. A saved receipt must be protected by household-level authorization independent of public file URLs.
5. Distinguish **created by**, **paid by**, **account from which paid**, **personal/shared allocation**, and **category**; they are not interchangeable.
6. A transfer between tracked owned accounts is not an expense; credit-card payments must not count as new spending.
7. Money is stored/processed using exact decimal representations, never binary floating point for amounts.
8. Multi-user edits require defined revision/conflict handling and audit trail.
9. Duplicate receipt/import detection must not silently delete or coalesce different legitimate purchases.
10. Returns, rebates, discounts, bottle deposits, vouchers, rounding and VAT require explicit cases and expected outputs before development.

## Questions to resolve in A2–A4
- Are line items always required for purchases with a receipt? Can one line be split between categories?
- Should uncategorized individual lines inherit the header category, or block confirmation?
- Item identity: exact printed name, edited display name, canonical product, barcode, brand, size, multiple units?
- Does a multi-line receipt always map to one payment, including split tenders?
- Who can edit/remove a confirmed family transaction? Do edits alter historical reports?
- How should partial refunds, coupons, discounts across items and non-item receipts be modeled?

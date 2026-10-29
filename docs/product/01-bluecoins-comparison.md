# Bluecoins reference and gap analysis (2026-10-29)

Sources (official documentation):
- https://www.bluecoinsapp.com/guide/transactions/
- https://www.bluecoinsapp.com/guide/budget/
- https://www.bluecoinsapp.com/guide/categories/
- https://www.bluecoinsapp.com/guide/filters/
- https://www.bluecoinsapp.com/guide/import-export/
- https://www.bluecoinsapp.com/settings/online-sync/
- https://www.bluecoinsapp.com/faq/

These are research notes, NOT claims that our replacement is already built. Do not copy Bluecoins UI art, logos, trademarked branding, or code.

| Area | Bluecoins documented behavior | Our intended direction / open questions |
| --- | --- | --- |
| Transactions | Expense, income and transfer; amount, category, account, date/time, payee, notes, labels, attachments and status | Preserve fast entry; add product lines and personal/household attribution |
| Splits | One transaction can be split into multiple category/account/note legs | Distinguish **financial allocation** from **itemized receipt lines**; determine whether both are required |
| Category taxonomy | Group/type/category hierarchy and configurable budgets | Main category + optional subcategory, editable on both header and lines; define inheritance |
| Budgets | Category/type daily-through-annual and custom month-by-month budgets, with budget-vs-actual reports | Family-wide monthly ceiling, possibly incomes, category/member limits and rollover |
| Filters and reports | Date ranges, categories, accounts, tags, statuses; charts and exports | Product names, quantities, unit prices, merchant, household member and comparison over time |
| Family sharing | Sync/backup using the same linked Google Drive/Dropbox/OneDrive account | Separate users with shared household workspace, audit metadata, permission boundaries |
| Web | No desktop/web application per official FAQ | Full browser support |
| Receipts | Store attached files/images | Image/PDF -> structured draft, validation, editable lines, approval |
| Data portability | CSV/QIF transaction import; PDF/CSV/HTML report exports | Safe migration (if approved); genuine XLSX generation; stable data export and backup |

## Findings that affect migration
Bluecoins' documented CSV templates represent a transaction with columns such as type, date, item/payee, amount, parent category, category, account, labels, status, and split. They do **not** promise structured per-product receipt data. Importing the historical transaction ledger may be possible; reconstructing individual products from past aggregate transactions may not be possible.

## Must reproduce carefully before implementation
- Expense and income must not double count transfers between owned accounts.
- One transaction can contain split allocations and/or multiple receipt products without confusing the two.
- Dates, currencies, partial refunds, discounts, duplicate imports, and reconciliation behavior must be explicitly specified.
- Historical labels/categories must be mapped with a reversible preview before migration.

## Validation before coding
Interview the household about exactly which Bluecoins screens/flows they rely on most, and which interactions are frustrating. Screenshots or sample exports may be discussed **privately**, not committed to this public repository.

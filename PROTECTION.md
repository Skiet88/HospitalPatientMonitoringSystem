# Branch Protection Rules (Assignment 13)

This repository uses branch protection on `main` to ensure code quality and collaboration discipline.

## Configured Rules

1. Require pull request before merging
2. Require at least 1 approving review
3. Require status checks to pass before merging
4. Include administrators in these restrictions
5. Disallow direct pushes to `main`

## Why These Rules Matter

- Prevents unreviewed code from reaching production-ready branches.
- Enforces automated validation through CI before merge.
- Reduces regressions by requiring test pass evidence on every PR.
- Supports traceability by making changes visible through review history.
- Aligns with industry workflow where `main` remains stable and releasable.

## Required Status Check

The required check is the workflow job from `.github/workflows/ci.yml`:

- `Build and Test`

When this check fails, GitHub blocks the PR merge until the issue is fixed.

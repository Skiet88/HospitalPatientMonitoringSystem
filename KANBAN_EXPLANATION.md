# kanban_explanation.md — Kanban Board Definition and Purpose
## Hospital Patient Monitoring System (HPMS)

---

## 1. What is a Kanban Board?

A Kanban board is a visual project management tool that represents work as cards moving across columns, where each column represents a stage in the workflow. The word "Kanban" comes from Japanese, meaning "visual signal" or "card" — reflecting its core purpose of making work visible at a glance.

At its simplest, a Kanban board answers three questions for every task:
- **What needs to be done?** (To Do)
- **What is being worked on right now?** (In Progress)
- **What has been completed?** (Done)

In software development, Kanban boards are used to manage user stories, bug fixes, and development tasks across the full lifecycle of a sprint.

---

## 2. How the HPMS Kanban Board Works

### 2.1 Visualizing Workflow

The HPMS board uses five columns to represent the complete lifecycle of a development task:

| Column | Purpose |
|---|---|
| **To Do** | User stories and tasks not yet started; the full product backlog lives here |
| **In Progress** | Tasks actively being developed; limited to Sprint 1 selected stories |
| **Testing** | Tasks where development is complete but unit/integration testing is still pending |
| **Blocked** | Tasks that cannot proceed due to a dependency, bug, or external factor |
| **Done** | Fully developed, tested, and verified tasks |

Each column makes the state of every task immediately visible — there is no need to ask "where is this task?" The board answers that question visually.

### 2.2 Limiting Work-in-Progress (WIP)

One of Kanban's core principles is limiting the number of tasks in any single column at once. For HPMS, the **In Progress** column is limited to **3 tasks at a time**. This prevents context-switching overload — a common problem for solo developers who try to work on too many things simultaneously.

The **Blocked** column serves a similar function: isolating stuck tasks so they do not pollute the In Progress column and inflate the apparent workload.

### 2.3 Supporting Agile Principles

The HPMS Kanban board supports Agile principles in the following ways:

**Continuous Delivery:** Tasks flow steadily from To Do → In Progress → Testing → Done rather than being batched into large releases. Each completed user story delivers incremental value.

**Adaptability:** New tasks can be added to the To Do column at any time as requirements evolve. The board does not need to be restructured — new cards simply enter the flow.

**Transparency:** The board is public on GitHub, meaning any stakeholder (professor, future team member) can see the current state of development at any time without requesting a status update.

**Focus:** The WIP limit on In Progress enforces focus — work must be completed and moved to Testing before new work can be pulled in. This mirrors the Agile principle of finishing work rather than starting work.

---

## 3. Customization Choices

Two custom columns were added beyond the standard Automated Kanban template:

**Testing Column:** Added to reflect the quality assurance requirements of a healthcare system. In HPMS, no user story can move to Done without passing unit and integration tests. The Testing column makes this mandatory step visible in the workflow rather than treating it as an invisible part of "In Progress."

**Blocked Column:** Added to isolate tasks that are stuck due to dependencies or technical blockers. Without this column, blocked tasks would sit in In Progress indefinitely, misrepresenting the true state of the sprint. Making blockages visible is the first step to resolving them quickly.

These customizations align the board with real-world clinical software development practices, where testing and risk management are non-negotiable.
# template_analysis.md — GitHub Project Template Analysis
## Hospital Patient Monitoring System (HPMS)

---

## 1. Template Comparison Table

| Feature | Basic Kanban | Automated Kanban | Bug Triage | Team Planning |
|---|---|---|---|---|
| **Default Columns** | To Do, In Progress, Done | To Do, In Progress, Done | Needs Triage, High Priority, Low Priority, Closed | To Do, In Progress, Done |
| **Automation** | None — all movement is manual | Auto-moves issues to In Progress when opened; auto-moves to Done when closed | Auto-triages new issues into Needs Triage | Auto-moves based on PR and issue events |
| **WIP Limits** | Not built-in | Not built-in | Not built-in | Not built-in |
| **Issue Linking** | Manual | Automatic via issue/PR events | Automatic via issue events | Manual |
| **Pull Request Integration** | No | Yes — PRs auto-move on open/merge | No | Yes |
| **Best For** | Small projects, simple workflows, beginners | Agile sprint tracking, active development teams | Teams managing large volumes of bugs | Multi-team planning across features |
| **Agile Suitability** | Low — no automation to support sprints | High — mirrors Agile sprint lifecycle | Medium — focused on defects only | Medium — good for planning but limited automation |
| **Customizability** | High — fully manual, easy to adapt | Medium — automation rules may conflict with custom columns | Low — designed for bug workflow specifically | Medium |

---

## 2. Chosen Template: Automated Kanban

### Justification

The **Automated Kanban** template was selected for HPMS for the following reasons:

**1. Agile Sprint Alignment**
HPMS is being developed using Agile methodology with defined sprints (established in Assignment 6). The Automated Kanban template mirrors the Agile sprint lifecycle — tasks flow from To Do → In Progress → Done automatically as issues are opened and closed, reducing manual overhead and keeping the board accurate in real time.

**2. Issue and PR Automation**
As development progresses, every GitHub Issue (user story) and Pull Request will automatically move across columns based on its state. This ensures the board always reflects the true state of the sprint without requiring manual updates after every code commit.

**3. Scalability for Future Sprints**
The template is easy to extend with additional columns (Testing, Blocked) to reflect HPMS's quality assurance requirements — critical for a healthcare system where untested code cannot be deployed.

**4. Visibility for Solo Development**
Even as a solo developer, the automated movement of tasks provides an honest, real-time view of progress — making it easier to identify bottlenecks and stay focused on sprint goals.

**Why not the others:**
- *Basic Kanban* — fully manual; too much overhead to maintain accurately during active development
- *Bug Triage* — focused exclusively on defect management; not suitable as a primary sprint board
- *Team Planning* — better suited for multi-team coordination; overkill for a single-developer project
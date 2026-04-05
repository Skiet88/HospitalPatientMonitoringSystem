# AGILE_PLANNING.md — Agile User Stories, Backlog, and Sprint Planning
## Hospital Patient Monitoring System (HPMS)

> Builds on: [SPECIFICATION.md](./SPECIFICATION.md) | [SRD.md](./SRD.md) | [STAKEHOLDERS.md](./STAKEHOLDERS.md)

---

## 1. User Stories

Converted from functional requirements (Assignment 4) and use cases (Assignment 5) using the format: *"As a [role], I want [action] so that [benefit]."*

| Story ID | User Story | Mapped Requirement | Acceptance Criteria | Priority |
|---|---|---|---|---|
| US-001 | As a **Nurse**, I want to register a new patient so that their vitals can be monitored from admission. | FR-01 | Patient record created within 5 seconds; mandatory fields enforced; patient appears on ward dashboard immediately. | High |
| US-002 | As a **Nurse**, I want to see a live dashboard of all my ward's patient vitals so that I can monitor everyone at a glance without checking each patient manually. | FR-03 | Dashboard refreshes every 2 seconds automatically; patients with active alerts are highlighted in red. | High |
| US-003 | As a **Nurse**, I want to receive instant alerts when a patient's vitals breach thresholds so that I can respond before the situation becomes critical. | FR-05, FR-06 | Alert notification appears within 3 seconds of threshold breach; alert shows patient name, vital type, and severity. | High |
| US-004 | As a **Nurse**, I want to acknowledge alerts so that the system and my team know I am aware and responding. | FR-07 | Acknowledged alert is visually distinct; acknowledgement logged with my name and timestamp; cannot be undone. | High |
| US-005 | As a **Doctor**, I want to configure alert thresholds per patient so that monitoring is tailored to each patient's clinical condition. | FR-04 | Threshold changes take effect immediately; per-patient overrides are clearly distinguished from system defaults. | High |
| US-006 | As a **Doctor**, I want to view a patient's vital history so that I can identify trends and adjust treatment plans accordingly. | FR-08 | History loads within 3 seconds for up to 30 days; data shown in both chart and table format; filterable by vital type. | High |
| US-007 | As a **Doctor**, I want to receive email notifications for critical alerts so that I am informed even when I am not logged into the system. | FR-06 | Critical alert email delivered within 60 seconds; email includes patient name, vital type, triggered value, and timestamp. | High |
| US-008 | As a **Ward Manager**, I want a ward summary dashboard so that I can oversee all patients and active alerts across my ward at once. | FR-11 | Summary shows total patients, bed occupancy, and alert counts by severity; updates in real time. | Medium |
| US-009 | As an **Admin**, I want to manage user accounts so that only authorized staff can access the system. | FR-09 | Admin can create, deactivate, and assign roles to users; role changes take effect on next login. | High |
| US-010 | As a **Doctor**, I want to generate a PDF report of a patient's vitals and alert history so that I can include it in clinical reviews and handovers. | FR-10 | Report generated within 10 seconds; includes vital trend charts, alert log, and acknowledgement records. | Medium |
| US-011 | As a **Compliance Officer**, I want an immutable audit log of all system actions so that I can verify regulatory compliance and investigate incidents. | FR-12 | All login, access, threshold change, and alert acknowledgement events are logged; log is exportable as CSV; no entry can be deleted. | Medium |
| US-012 | As a **System Admin**, I want all patient data encrypted with AES-256 so that security compliance is met and patient privacy is protected. | NFR-SE01 | Security scan confirms AES-256 encryption at rest; TLS 1.2+ in transit; no plaintext patient data in database. | High |

---

## 2. Product Backlog

Prioritized using **MoSCoW** method. Effort estimated in **story points** using Fibonacci sequence (1, 2, 3, 5, 8).

| Story ID | User Story | MoSCoW Priority | Effort (Points) | Dependencies | Justification |
|---|---|---|---|---|---|
| US-001 | Register new patient | Must-have | 2 | None | Core entry point; no monitoring possible without patient records |
| US-002 | Live vitals dashboard | Must-have | 5 | US-001 | Primary nurse interface; central to MVP value |
| US-003 | Receive breach alerts | Must-have | 5 | US-002, US-005 | Critical safety feature; directly addresses nurse pain point |
| US-004 | Acknowledge alerts | Must-have | 2 | US-003 | Required for accountability and audit trail |
| US-005 | Configure alert thresholds | Must-have | 3 | US-001 | Alerts are meaningless without configurable thresholds |
| US-006 | View vital history | Must-have | 3 | US-001 | Doctors need trend data for clinical decisions |
| US-007 | Email notifications for critical alerts | Should-have | 3 | US-003 | Important for doctors off-system; not blocking MVP |
| US-009 | Manage user accounts | Must-have | 3 | None | Required before any other role can use the system |
| US-012 | AES-256 data encryption | Must-have | 3 | None | Non-negotiable security baseline |
| US-008 | Ward summary dashboard | Should-have | 3 | US-002 | Valuable for Ward Manager but not MVP-blocking |
| US-010 | Generate PDF reports | Could-have | 3 | US-006 | Useful but not required for core monitoring function |
| US-011 | Audit log | Could-have | 2 | US-009 | Compliance need; important but not Day 1 MVP |

### Prioritization Justification

**Must-have stories (US-001, 002, 003, 004, 005, 006, 009, 012)** align directly with the core stakeholder success metrics: nurses need real-time monitoring and alerts; doctors need configurable thresholds and vital history; the system cannot operate without user management and security. These form the MVP.

**Should-have stories (US-007, 008)** add significant value but the system is functional without them in the first sprint.

**Could-have stories (US-010, 011)** are important for compliance and clinical workflow but can be delivered in later sprints without blocking core functionality.

---

## 3. Sprint Planning

### Sprint Goal
> *"Deliver a working patient registration, live vitals dashboard, and alert system that allows nurses to monitor patients and respond to threshold breaches — the core MVP of HPMS."*

### Sprint Duration: 2 Weeks

### Selected User Stories for Sprint 1

| Story ID | User Story | Effort (Points) |
|---|---|---|
| US-009 | Manage user accounts (Admin) | 3 |
| US-001 | Register new patient | 2 |
| US-005 | Configure alert thresholds | 3 |
| US-002 | Live vitals dashboard | 5 |
| US-003 | Receive breach alerts | 5 |
| US-004 | Acknowledge alerts | 2 |

**Total Sprint Points: 20**

---

### Sprint Backlog — Task Breakdown

| Task ID | Task Description | Assigned To | Estimated Hours | Status |
|---|---|---|---|---|
| T-001 | Set up project repo, Docker Compose, and base Express server | Dev Team | 4 | To Do |
| T-002 | Create PostgreSQL schema: users, patients, vitals, alerts, thresholds | Dev Team | 4 | To Do |
| T-003 | Implement JWT authentication and role-based middleware | Dev Team | 6 | To Do |
| T-004 | Build user management API (CRUD for users, role assignment) | Dev Team | 5 | To Do |
| T-005 | Build Admin UI: user management page | Dev Team | 4 | To Do |
| T-006 | Build patient registration API endpoint | Dev Team | 3 | To Do |
| T-007 | Build patient registration form UI | Dev Team | 3 | To Do |
| T-008 | Build vital signs ingestion API endpoint (POST /vitals/ingest) | Dev Team | 4 | To Do |
| T-009 | Implement sensor simulator script (generates random vitals every 5s) | Dev Team | 3 | To Do |
| T-010 | Set up Redis cache for latest vitals per patient | Dev Team | 3 | To Do |
| T-011 | Build WebSocket server for real-time vital push to frontend | Dev Team | 5 | To Do |
| T-012 | Build live vitals dashboard UI (ward view with per-patient cards) | Dev Team | 6 | To Do |
| T-013 | Build alert threshold configuration API and UI | Dev Team | 4 | To Do |
| T-014 | Implement alert engine: threshold evaluation on vital ingest | Dev Team | 5 | To Do |
| T-015 | Implement in-app alert notification (WebSocket push to nurse dashboard) | Dev Team | 4 | To Do |
| T-016 | Build alert acknowledgement API endpoint and UI button | Dev Team | 3 | To Do |
| T-017 | Write unit tests for alert engine and vitals service | Dev Team | 4 | To Do |
| T-018 | Integration testing: end-to-end vital ingest → alert → acknowledgement | Dev Team | 3 | To Do |

**Total Estimated Hours: 77 hours**

---

## 4. Traceability Summary

| User Story | Assignment 4 Requirement | Assignment 5 Use Case |
|---|---|---|
| US-001 | FR-01 Patient Registration | UC-01 Register Patient |
| US-002 | FR-03 Live Vitals Dashboard | UC-03 Monitor Live Vitals |
| US-003 | FR-05 Alert Generation | UC-05 Trigger Alert |
| US-004 | FR-07 Alert Acknowledgement | UC-06 Acknowledge Alert |
| US-005 | FR-04 Configurable Thresholds | UC-04 Configure Thresholds |
| US-006 | FR-08 Vital History | UC-07 View Vital History |
| US-007 | FR-06 Alert Notifications | UC-05 Trigger Alert |
| US-008 | FR-11 Ward Summary | UC-08 View Ward Summary |
| US-009 | FR-09 Role-Based Access | UC-02 Manage Users |
| US-010 | FR-10 Report Generation | UC-09 Generate Report |
| US-011 | FR-12 Audit Log | UC-10 Export Audit Log |
| US-012 | NFR-SE01 Encryption | — |
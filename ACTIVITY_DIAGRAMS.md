# ACTIVITY_DIAGRAMS.md — Activity Workflow Modeling
## Hospital Patient Monitoring System (HPMS)

> Traces to: [SRD.md](./SRD.md) | [STAKEHOLDERS.md](./STAKEHOLDERS.md) | [AGILE_PLANNING.md](./AGILE_PLANNING.md)

---

## 1. Patient Registration Workflow

```mermaid
flowchart TD
    A([Start]) --> B[Nurse opens registration form]
    B --> C[Nurse enters patient details\nname, DOB, diagnosis, ward, bed]
    C --> D{All mandatory\nfields filled?}
    D -- No --> E[System highlights missing fields]
    E --> C
    D -- Yes --> F[System validates data format]
    F --> G{Data valid?}
    G -- No --> H[System shows validation errors]
    H --> C
    G -- Yes --> I[System checks bed availability]
    I --> J{Bed available\nin selected ward?}
    J -- No --> K[System suggests alternative beds]
    K --> C
    J -- Yes --> L[System creates patient record]
    L --> M[System assigns patient to bed]
    M --> N[Patient appears on ward dashboard]
    N --> O[Nurse notified of successful registration]
    O --> P([End])

    style A fill:#2d6a4f,color:#fff
    style P fill:#c1121f,color:#fff
```

### Explanation
This workflow maps to **FR-01 (Patient Registration)** and **US-001**. The decision nodes handle the two most common failure points: incomplete form submission and unavailable beds. Parallel to record creation, the ward dashboard updates in real time — addressing the Ward Manager's concern for live occupancy visibility (STAKEHOLDERS.md).

**Stakeholder addressed:** Nurse (primary actor), Ward Manager (dashboard update)
**Sprint Task:** T-006, T-007

---

## 2. Vital Signs Ingestion and Alert Generation Workflow

```mermaid
flowchart TD
    A([Start]) --> B[IoT Sensor transmits vital reading]
    B --> C[API Server receives POST /vitals/ingest]
    C --> D[System validates payload format]
    D --> E{Payload valid?}
    E -- No --> F[System logs rejected reading\nreturns 400 error]
    F --> Z([End])
    E -- Yes --> G[System stores reading in PostgreSQL]
    G --> H[System updates Redis cache\nwith latest vitals]
    H --> I[Alert Engine evaluates reading\nagainst thresholds]
    I --> J{Threshold\nbreached?}
    J -- No --> K[WebSocket pushes updated vitals\nto nurse dashboard]
    K --> Z
    J -- Yes --> L{Severity?}
    L -- Warning --> M[System generates Warning alert]
    L -- Critical --> N[System generates Critical alert]
    M --> O[In-app notification sent to nurse]
    N --> O
    N --> P[Email notification sent to doctor]
    O --> Q[Alert displayed on dashboard]
    P --> Q
    Q --> K

    style A fill:#2d6a4f,color:#fff
    style Z fill:#c1121f,color:#fff
```

### Explanation
This is the core end-to-end workflow of HPMS, mapping to **FR-02, FR-05, FR-06** and **US-002, US-003, US-007**. The parallel actions — in-app notification to nurse AND email to doctor for critical alerts — directly address the doctor's pain point of missing critical updates when off-system.

**Stakeholder addressed:** Nurse (in-app alert), Doctor (email notification), Ward Manager (dashboard)
**Sprint Tasks:** T-008, T-009, T-010, T-011, T-014, T-015

---

## 3. Alert Acknowledgement Workflow

```mermaid
flowchart TD
    A([Start]) --> B[Alert appears on nurse dashboard]
    B --> C[Nurse reviews alert details\npatient, vital type, severity, value]
    C --> D[Nurse clicks Acknowledge button]
    D --> E[System records acknowledgement\nwith nurse ID and timestamp]
    E --> F[Alert status updated to Acknowledged]
    F --> G[Dashboard updates alert visual state]
    G --> H{Was alert Critical?}
    H -- Yes --> I[System notifies doctor\nalert has been acknowledged]
    H -- No --> J[Audit log entry created]
    I --> J
    J --> K{Are patient vitals\nreturning to normal?}
    K -- Yes --> L[System auto-resolves alert]
    K -- No --> M[Alert remains Acknowledged\nnurse continues monitoring]
    L --> N([End])
    M --> N

    style A fill:#2d6a4f,color:#fff
    style N fill:#c1121f,color:#fff
```

### Explanation
Maps to **FR-07 (Alert Acknowledgement)** and **US-004**. The audit log entry on every acknowledgement directly addresses the Compliance Officer's requirement for a traceable record of all clinical responses. The doctor notification on critical acknowledgement closes the communication loop between nurses and doctors.

**Stakeholder addressed:** Nurse (primary actor), Doctor (notification), Compliance Officer (audit)
**Sprint Task:** T-016

---

## 4. User Login and Authentication Workflow

```mermaid
flowchart TD
    A([Start]) --> B[User navigates to login page]
    B --> C[User enters email and password]
    C --> D[System checks failed attempt count]
    D --> E{Account\nlocked out?}
    E -- Yes --> F[System displays lockout message\nwith remaining time]
    F --> Z([End])
    E -- No --> G[System validates credentials]
    G --> H{Credentials\nvalid?}
    H -- No --> I[System increments failed attempt count]
    I --> J{Failed attempts\n>= 5 in 1 minute?}
    J -- Yes --> K[System locks account for 15 minutes]
    K --> L[System logs lockout event to audit trail]
    L --> Z
    J -- No --> M[System displays invalid credentials error]
    M --> C
    H -- Yes --> N[System issues JWT token]
    N --> O[System logs successful login to audit trail]
    O --> P{User role?}
    P -- Nurse --> Q[Redirect to Ward Dashboard]
    P -- Doctor --> R[Redirect to Patient Overview]
    P -- Admin --> S[Redirect to Admin Panel]
    Q --> Z
    R --> Z
    S --> Z

    style A fill:#2d6a4f,color:#fff
    style Z fill:#c1121f,color:#fff
```

### Explanation
Maps to **FR-09 (Role-Based Access)**, **NFR-SE02, NFR-SE03** and **US-009, US-012**. The role-based redirect after login ensures each user sees only the interface relevant to their responsibilities — a key usability requirement. Account lockout after 5 failed attempts addresses the IT Staff and Compliance Officer's security concerns.

**Stakeholder addressed:** All users (primary), IT Staff (security), Compliance Officer (audit)
**Sprint Task:** T-003

---

## 5. Alert Threshold Configuration Workflow

```mermaid
flowchart TD
    A([Start]) --> B[Doctor navigates to patient profile]
    B --> C[Doctor selects Configure Thresholds]
    C --> D[System loads current thresholds\ndefault or existing override]
    D --> E[Doctor selects vital type to configure\nheart rate, SpO2, BP, temp, resp rate]
    E --> F[Doctor enters min and max values]
    F --> G{Values within\nphysiological range?}
    G -- No --> H[System shows physiological range warning]
    H --> F
    G -- Yes --> I[System saves threshold override\nlinked to patient ID]
    I --> J[System marks threshold as patient override]
    J --> K[Alert Engine immediately applies\nnew thresholds to incoming readings]
    K --> L[System logs threshold change to audit trail]
    L --> M[Doctor receives confirmation message]
    M --> N([End])

    style A fill:#2d6a4f,color:#fff
    style N fill:#c1121f,color:#fff
```

### Explanation
Maps to **FR-04 (Configurable Alert Thresholds)** and **US-005**. The immediate application of new thresholds (step K) is critical — a doctor adjusting thresholds for a deteriorating patient needs the change to take effect on the next vital reading, not after a delay. The audit log entry addresses the Compliance Officer's need to track all clinical configuration changes.

**Stakeholder addressed:** Doctor (primary actor), Compliance Officer (audit trail)
**Sprint Task:** T-013

---

## 6. Report Generation Workflow

```mermaid
flowchart TD
    A([Start]) --> B[Authorized user navigates to Reports]
    B --> C[User selects patient and date range]
    C --> D[User clicks Generate Report]
    D --> E[System queries vital history\nfor selected date range]
    E --> F[System queries alert log\nfor selected date range]
    F --> G[System compiles data into report structure]
    G --> H{Data available\nfor date range?}
    H -- No --> I[System displays no data message]
    I --> Z([End])
    H -- Yes --> J[System generates vital trend charts]
    J --> K[System generates alert log table\nwith acknowledgement records]
    K --> L{Generation time\nwithin 10 seconds?}
    L -- No --> M[System displays timeout error\nsuggests narrower date range]
    M --> Z
    L -- Yes --> N[PDF report ready for download]
    N --> O[System logs report generation\nto audit trail]
    O --> P[User downloads PDF]
    P --> Z

    style A fill:#2d6a4f,color:#fff
    style Z fill:#c1121f,color:#fff
```

### Explanation
Maps to **FR-10 (Report Generation)** and **US-010**. The 10-second generation guard condition directly reflects the acceptance criteria in SRD.md. The parallel generation of vital charts and alert tables ensures the report is comprehensive for clinical review and handover documentation.

**Stakeholder addressed:** Doctor (clinical review), Hospital Administrator (compliance reporting), Compliance Officer (audit)
**Sprint Task:** Not in Sprint 1 — Could-have backlog item

---

## 7. User Account Management Workflow

```mermaid
flowchart TD
    A([Start]) --> B[Admin navigates to User Management]
    B --> C{Action?}
    C -- Create User --> D[Admin enters name, email, role, ward]
    D --> E{All fields\nvalid?}
    E -- No --> F[System highlights errors]
    F --> D
    E -- Yes --> G[System creates user account]
    G --> H[System sends welcome email\nwith temporary password]
    H --> I[Audit log entry created]
    I --> Z([End])
    C -- Deactivate User --> J[Admin selects user to deactivate]
    J --> K[System deactivates account immediately]
    K --> L[Active sessions invalidated]
    L --> I
    C -- Change Role --> M[Admin selects user and new role]
    M --> N[System updates role]
    N --> O[Change takes effect on next login]
    O --> I

    style A fill:#2d6a4f,color:#fff
    style Z fill:#c1121f,color:#fff
```

### Explanation
Maps to **FR-09 (Role-Based Access Control)** and **US-009**. The immediate session invalidation on deactivation (step L) is a critical security requirement — a deactivated user must not be able to continue an active session. All three admin actions feed into the audit log, addressing the Compliance Officer's traceability requirement.

**Stakeholder addressed:** Admin (primary actor), IT Staff (security), Compliance Officer (audit)
**Sprint Tasks:** T-003, T-004, T-005

---

## 8. Ward Dashboard Monitoring Workflow

```mermaid
flowchart TD
    A([Start]) --> B[Nurse logs in and lands on Ward Dashboard]
    B --> C[System loads all patients\nassigned to nurse's ward]
    C --> D[System fetches latest vitals\nfrom Redis cache for each patient]
    D --> E[Dashboard renders patient cards\nwith live vital values]
    E --> F[WebSocket connection established]
    F --> G[System pushes real-time updates\nevery 2 seconds]
    G --> H{New vital\nreceived?}
    H -- Yes --> I[Dashboard updates patient card]
    I --> J{Alert triggered\nfor this reading?}
    J -- Yes --> K[Alert banner displayed\non patient card]
    K --> L[Nurse reviews alert]
    L --> M{Nurse\nacknowledges?}
    M -- Yes --> N[Alert moves to Acknowledged state]
    M -- No --> O{Unacknowledged\n> 5 minutes?}
    O -- Yes --> P[Alert escalated to doctor]
    O -- No --> L
    N --> G
    P --> G
    J -- No --> G
    H -- No --> G

    style A fill:#2d6a4f,color:#fff
```

### Explanation
Maps to **FR-03 (Live Vitals Dashboard)**, **FR-05, FR-07** and **US-002, US-003, US-004**. This workflow captures the continuous, real-time nature of HPMS — unlike other workflows that have a clear end state, ward monitoring is a perpetual loop that only terminates when the nurse logs out. The 5-minute escalation path addresses the risk of a nurse missing a critical alert.

**Stakeholder addressed:** Nurse (primary actor), Ward Manager (overview), Doctor (escalation)
**Sprint Tasks:** T-010, T-011, T-012, T-015, T-016

---

## Traceability Summary

| Activity Diagram | Functional Requirements | User Stories | Sprint Tasks |
|---|---|---|---|
| Patient Registration | FR-01 | US-001 | T-006, T-007 |
| Vital Ingestion & Alert Generation | FR-02, FR-05, FR-06 | US-002, US-003, US-007 | T-008 to T-015 |
| Alert Acknowledgement | FR-07 | US-004 | T-016 |
| Login & Authentication | FR-09, NFR-SE02, NFR-SE03 | US-009, US-012 | T-003 |
| Threshold Configuration | FR-04 | US-005 | T-013 |
| Report Generation | FR-10, FR-12 | US-010, US-011 | — |
| User Account Management | FR-09, FR-12 | US-009, US-011 | T-003, T-004, T-005 |
| Ward Dashboard Monitoring | FR-03, FR-05, FR-07 | US-002, US-003, US-004 | T-010, T-011, T-012 |

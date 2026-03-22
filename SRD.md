# SRD.md — System Requirements Document
## Hospital Patient Monitoring System (HPMS)

---

## 1. Introduction

This System Requirements Document (SRD) defines the functional and non-functional requirements for the Hospital Patient Monitoring System (HPMS). All requirements are traceable to stakeholder concerns identified in [STAKEHOLDERS.md](./STAKEHOLDERS.md) and build upon the system specification in [SPECIFICATION.md](./SPECIFICATION.md).

---

## 2. Functional Requirements

Each requirement follows the format: **"The system shall..."** with an acceptance criterion.

---

### FR-01 — Patient Registration
**The system shall allow authorized nurses and admins to register new patients by capturing their name, date of birth, diagnosis, assigned doctor, ward, and bed number.**

- *Stakeholder:* Nurse, Hospital Administrator
- *Acceptance Criteria:* A patient record is created and visible on the ward dashboard within 5 seconds of submission. Mandatory fields (name, DOB, ward) cannot be left blank.

---

### FR-02 — Real-Time Vital Signs Ingestion
**The system shall ingest vital sign readings (heart rate, blood pressure, SpO2, temperature, respiratory rate) from bedside sensors or the simulator and store each reading with a timestamp.**

- *Stakeholder:* Nurse, Doctor, Ward Manager
- *Acceptance Criteria:* Vital readings appear on the patient dashboard within 2 seconds of being transmitted. Each reading is stored with patient ID, timestamp, and source device ID.

---

### FR-03 — Live Vitals Dashboard
**The system shall display a real-time dashboard showing the latest vital readings for all patients assigned to the logged-in nurse's ward.**

- *Stakeholder:* Nurse, Ward Manager
- *Acceptance Criteria:* Dashboard refreshes automatically every 2 seconds without requiring a manual page reload. Patients with active alerts are visually highlighted.

---

### FR-04 — Configurable Alert Thresholds
**The system shall allow doctors and admins to configure alert thresholds per vital sign, with system-wide defaults and per-patient overrides.**

- *Stakeholder:* Doctor, IT Staff
- *Acceptance Criteria:* Changes to thresholds take effect immediately for subsequent readings. Default thresholds are pre-loaded on system setup.

---

### FR-05 — Automated Alert Generation
**The system shall automatically generate an alert when a patient's vital reading breaches the configured threshold, categorized by severity (Warning, Critical).**

- *Stakeholder:* Nurse, Doctor, Ward Manager
- *Acceptance Criteria:* An alert is generated within 1 second of a threshold breach. The alert record includes patient ID, vital type, triggered value, severity, and timestamp.

---

### FR-06 — Alert Notification Delivery
**The system shall notify the assigned nurse via an in-app notification and the assigned doctor via in-app notification and email for Critical-severity alerts.**

- *Stakeholder:* Nurse, Doctor
- *Acceptance Criteria:* In-app notifications appear within 3 seconds of alert generation. Critical alert emails are delivered within 60 seconds. Notification delivery is logged.

---

### FR-07 — Alert Acknowledgement
**The system shall allow nurses to acknowledge alerts, recording the acknowledging user and timestamp.**

- *Stakeholder:* Nurse, Ward Manager, Compliance Officer
- *Acceptance Criteria:* Acknowledged alerts are visually distinct from unacknowledged ones. Acknowledgement is logged with user ID and timestamp and cannot be deleted.

---

### FR-08 — Patient Vital History
**The system shall allow doctors and nurses to view a patient's historical vital readings, filterable by vital type and date/time range.**

- *Stakeholder:* Doctor, Nurse
- *Acceptance Criteria:* History loads within 3 seconds for up to 30 days of data. Data is displayed in a time-series chart and a tabular format.

---

### FR-09 — Role-Based Access Control
**The system shall enforce role-based access so that Nurses can monitor and log, Doctors can review and update treatment notes, and Admins can manage users and system configuration.**

- *Stakeholder:* IT Staff, Compliance Officer, Hospital Administrator
- *Acceptance Criteria:* A nurse attempting to access admin configuration receives a 403 Forbidden response. Role permissions are configurable by Admin only.

---

### FR-10 — Report Generation
**The system shall allow authorized users to generate a PDF report of a patient's vital history and alert log for a specified date range.**

- *Stakeholder:* Doctor, Hospital Administrator, Compliance Officer
- *Acceptance Criteria:* Report is generated within 10 seconds. Report includes patient details, vital trend charts, and a full alert log with acknowledgement records.

---

### FR-11 — Ward Summary Dashboard
**The system shall provide a ward-level summary view showing total patients, active alerts by severity, and bed occupancy.**

- *Stakeholder:* Ward Manager, Hospital Administrator
- *Acceptance Criteria:* Summary updates in real time. Alert counts are broken down by Warning and Critical severity. Occupancy is shown as current/total beds.

---

### FR-12 — Audit Log
**The system shall maintain an immutable audit log of all login events, data access, alert acknowledgements, threshold changes, and user management actions.**

- *Stakeholder:* Compliance Officer, IT Staff
- *Acceptance Criteria:* Audit log entries cannot be deleted or modified by any user role. Logs are exportable by the Compliance Officer in CSV format.

---

## 3. Non-Functional Requirements

### 3.1 Usability

**NFR-U01:** The dashboard interface shall follow WCAG 2.1 Level AA accessibility standards, including sufficient colour contrast and keyboard navigability.
- *Measurable:* Passes automated accessibility audit with zero Level AA violations.

**NFR-U02:** Critical alert notifications shall use both colour (red) and an icon indicator so they are distinguishable by colour-blind users.
- *Measurable:* Alert visibility confirmed by colour-blindness simulation tool with no ambiguity.

---

### 3.2 Deployability

**NFR-D01:** The system shall be deployable on any Linux-based server (Ubuntu 20.04+) using Docker Compose with a single command (`docker-compose up`).
- *Measurable:* Full deployment completed in under 2 hours on a clean Ubuntu server.

**NFR-D02:** The system shall support environment-based configuration (development, staging, production) via `.env` files, with no hardcoded credentials.
- *Measurable:* Zero hardcoded secrets found in source code review.

---

### 3.3 Maintainability

**NFR-M01:** The codebase shall maintain a minimum of 70% unit test coverage across all API service modules.
- *Measurable:* Coverage report generated via CI pipeline; build fails below 70%.

**NFR-M02:** All API endpoints shall be documented in an OpenAPI 3.0 specification, accessible at `/api/docs` in development mode.
- *Measurable:* All endpoints listed in spec; no undocumented routes.

---

### 3.4 Scalability

**NFR-SC01:** The system shall support up to 100 concurrent patients and 50 concurrent active users in v1 without degradation in dashboard refresh performance.
- *Measurable:* Load test confirms p95 response time remains under 2 seconds at 50 concurrent users.

**NFR-SC02:** The database schema shall be designed to support multi-ward expansion without requiring structural changes to core tables.
- *Measurable:* Adding a new ward requires only a data insert, no schema migration.

---

### 3.5 Security

**NFR-SE01:** All patient data shall be encrypted at rest using AES-256 and in transit using TLS 1.2 or higher.
- *Measurable:* Security scan confirms AES-256 at rest and TLS 1.2+ in transit; no plaintext patient data in DB.

**NFR-SE02:** All API routes (except `/auth/login`) shall require a valid JWT token; tokens shall expire after 8 hours.
- *Measurable:* Unauthenticated requests to protected routes return 401. Token expiry enforced server-side.

**NFR-SE03:** Failed login attempts shall be rate-limited to 5 attempts per minute per IP address, after which the account is temporarily locked for 15 minutes.
- *Measurable:* 6th login attempt within 1 minute returns 429 Too Many Requests.

---

### 3.6 Performance

**NFR-P01:** Live vital readings shall appear on the patient dashboard within 2 seconds of being transmitted by the sensor.
- *Measurable:* End-to-end latency measured from sensor POST to dashboard update; p95 under 2 seconds.

**NFR-P02:** Alert generation (threshold evaluation and notification dispatch) shall complete within 1 second of a vital reading being ingested.
- *Measurable:* Timestamp delta between vital ingest and alert record creation is under 1 second in load tests.

**NFR-P03:** Patient vital history (up to 30 days) shall load within 3 seconds.
- *Measurable:* API response time for `/vitals/:patientId?range=30d` under 3 seconds at baseline load.

---

## 4. Requirements Traceability Matrix

| Requirement | Nurse | Doctor | Hospital Admin | IT Staff | Patient | Ward Manager | Compliance Officer |
|---|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| FR-01 Patient Registration | ✅ | | ✅ | | | | |
| FR-02 Vital Ingestion | ✅ | ✅ | | | ✅ | ✅ | |
| FR-03 Live Dashboard | ✅ | | | | | ✅ | |
| FR-04 Alert Thresholds | | ✅ | | ✅ | | | |
| FR-05 Alert Generation | ✅ | ✅ | | | | ✅ | |
| FR-06 Alert Notifications | ✅ | ✅ | | | | | |
| FR-07 Alert Acknowledgement | ✅ | | | | | ✅ | ✅ |
| FR-08 Vital History | ✅ | ✅ | | | | | |
| FR-09 Role-Based Access | | | ✅ | ✅ | | | ✅ |
| FR-10 Report Generation | | ✅ | ✅ | | | | ✅ |
| FR-11 Ward Summary | | | ✅ | | | ✅ | |
| FR-12 Audit Log | | | | ✅ | | | ✅ |

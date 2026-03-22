# REFLECTION.md — Challenges in Balancing Stakeholder Needs
## Hospital Patient Monitoring System (HPMS)

---

## Overview

Eliciting and balancing requirements for a hospital patient monitoring system revealed several genuine tensions between stakeholder priorities. This reflection documents the key conflicts encountered and how they were resolved.

---

## 1. Real-Time Performance vs. IT Infrastructure Constraints

**The conflict:** Nurses and doctors demanded that vital readings appear on dashboards instantly — ideally within 1 second. IT staff, however, flagged concerns about the bandwidth and processing overhead of maintaining persistent WebSocket connections for every active patient simultaneously, especially on hospital network infrastructure that is often shared with other critical systems.

**How it was balanced:** A 2-second threshold was agreed upon as the performance requirement (NFR-P01), which is clinically acceptable for monitoring stable patients while being technically achievable without saturating the network. A Redis cache layer was introduced in the architecture to serve the latest vitals quickly without hitting the database on every WebSocket push, easing the load on IT infrastructure.

---

## 2. Comprehensive Audit Logging vs. System Performance

**The conflict:** The Compliance Officer required an immutable, detailed audit log of every data access event — including every time a nurse views a patient's vitals. This level of logging, if written synchronously to the database, would add latency to every API call and potentially degrade dashboard performance for nurses during busy shifts.

**How it was balanced:** Audit logging was scoped to security-sensitive actions (logins, threshold changes, alert acknowledgements, report generation, user management) rather than every read operation. Routine vital reads are not individually logged, which keeps the audit trail meaningful without creating performance bottlenecks. This was accepted by the Compliance Officer as a reasonable trade-off given that access is already controlled by role-based authentication.

---

## 3. Patient Privacy vs. Clinical Accessibility

**The conflict:** The Data Privacy Officer pushed for strict access controls — ideally requiring doctors to explicitly request access to each patient's record individually, with a logged justification. Doctors and nurses, however, argued that in an emergency, any friction in accessing patient data could cost lives. They needed immediate access to any patient's vitals without approval workflows.

**How it was balanced:** Role-based access was designed to grant doctors and nurses broad read access to all patients within their assigned ward by default, without per-patient approval steps. Access outside one's ward requires Admin authorization. All access is logged in the audit trail, satisfying the Compliance Officer's need for accountability without creating dangerous delays in emergency situations.

---

## 4. Alert Sensitivity vs. Alert Fatigue

**The conflict:** Nurses expressed concern about alert fatigue — if thresholds are too sensitive, staff become desensitized to constant alarms and begin ignoring them, which defeats the purpose of the system. Doctors, on the other hand, wanted thresholds set conservatively (triggering early) to catch deterioration before it becomes critical.

**How it was balanced:** The system supports two alert severity levels (Warning and Critical) with separate thresholds, and allows per-patient threshold overrides by doctors. Nurses only receive push notifications for Critical alerts; Warning alerts appear on the dashboard passively. This gives doctors the early-warning sensitivity they need while protecting nurses from notification overload.

---

## 5. Simplicity for Nurses vs. Feature Depth for Administrators

**The conflict:** Nurses consistently prioritized a clean, fast, minimal interface — they are often working under pressure and cannot afford to navigate complex menus. Hospital administrators and the Ward Manager, however, wanted rich reporting, filtering, and configuration options that naturally add interface complexity.

**How it was balanced:** The UI was designed with role-specific views. Nurses see a streamlined ward dashboard focused entirely on vitals and alerts. Administrators and Ward Managers access a separate management portal with reporting and configuration tools. The same underlying system serves both audiences through different interface layers, keeping each view appropriate for its user without compromise.

---

## Key Lesson

The most important lesson from this requirements elicitation process was that **no stakeholder's needs exist in isolation**. Every design decision that satisfies one stakeholder creates a constraint for another. The role of a requirements engineer is not to pick winners, but to find trade-offs that are acceptable to all parties — ideally documenting those trade-offs explicitly so that future developers understand *why* a decision was made, not just *what* was decided.

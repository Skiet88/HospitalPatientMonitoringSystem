# AGILE_REFLECTION.md — Challenges in Agile Planning
## Hospital Patient Monitoring System (HPMS)

---

## Reflection: Balancing Prioritization, Estimation, and Agile Thinking as a Solo Developer

The professor noted that at this stage I am the only stakeholder. This reflection is therefore honest about the internal resistance and tension I experienced working through Agile planning alone.

---

### The Tension Between Wanting to Build Everything and Needing to Focus

The hardest part of sprint planning was not technical — it was psychological. After spending two assignments carefully specifying twelve functional requirements and nine non-functional requirements, there was a strong pull to include all of them in Sprint 1. Every requirement felt important. The alert system needs encryption. The dashboard needs audit logs. The reports need to be there from day one.

Agile, and specifically the MoSCoW method, forced a different mindset: what is the *minimum* that makes this system useful? The answer was uncomfortable but clear — a nurse needs to see live vitals and get alerted when something is wrong. Everything else, while valuable, can wait. Accepting that the first sprint would not produce a "complete" system felt like lowering standards, even though rationally it is the correct approach.

---

### Estimating Without Experience Is Genuinely Difficult

Story point estimation exposed a real gap. When I wrote the functional requirements in Assignment 4, I described *what* the system should do. Now I had to estimate *how hard* it would be to build each piece. These are very different skills.

For example, US-002 (Live Vitals Dashboard) was assigned 5 story points — the highest in the backlog. This felt right intuitively because it involves React state management, WebSocket integration, and real-time rendering. But am I certain? No. I have never built a real-time hospital dashboard before. There is a real risk that what I estimated as 5 points is actually 10, and that the sprint goal will not be met.

This uncertainty is uncomfortable. In a real Scrum team, estimates are collaborative — multiple developers discuss complexity and reach a consensus. Alone, estimation is a guess dressed up as a number. I tried to be honest about this by keeping sprint scope conservative (20 points) rather than overcommitting.

---

### Prioritization Felt Arbitrary Without Real User Feedback

MoSCoW prioritization assumes you understand what stakeholders value most. In Assignment 4, I reasoned through stakeholder concerns — but I never actually spoke to a nurse, a doctor, or a hospital administrator. This created a nagging uncertainty during backlog prioritization.

For instance, I marked the audit log (US-011) as "Could-have." But what if a compliance officer would consider it a Day 1 blocker? What if the hospital cannot legally deploy a monitoring system without audit trails in place? Without real stakeholder input, I cannot be certain my prioritization reflects reality. This is a genuine limitation of solo requirements engineering.

---

### Agile Feels Designed for Teams

Working through sprint planning alone made it clear that Agile methodology is fundamentally social. The daily standup assumes teammates. The sprint review assumes stakeholders to demo to. Retrospectives assume a team to reflect with. As a solo developer playing Product Owner, Scrum Master, and Development Team simultaneously, the process felt fragmented.

That said, the discipline of writing user stories forced clarity I would not have had otherwise. Breaking FR-03 (Live Vitals Dashboard) into T-009 through T-012 (simulator, Redis, WebSocket, UI) made the actual implementation path concrete. Whatever its limitations in a solo context, the Agile planning process produced a clearer picture of what Sprint 1 actually requires.

---

### What I Would Do Differently

If I were to repeat this process, I would conduct at least one informal interview with someone in a healthcare-adjacent role before prioritizing the backlog. Even a 15-minute conversation with a nurse or hospital IT administrator would dramatically improve the confidence behind prioritization decisions. Agile works best when the Product Owner has genuine stakeholder relationships — that is the piece this assignment, by necessity, had to simulate.

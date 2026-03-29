# Reflection — Challenges in Translating Requirements into Use Cases and Test Cases  
Hospital Patient Monitoring System (HPMS)

## Overview
Transforming the system requirements from Assignment 4 into structured use cases and test cases revealed several practical and conceptual challenges. While the requirements clearly defined what the system should achieve, translating them into user interactions and verifiable test scenarios required careful interpretation, refinement, and alignment with stakeholder expectations.

---

## 1. Abstract Requirements vs. Concrete User Interactions

**The challenge:**  
Functional requirements such as "The system shall provide real-time patient monitoring" are high-level and system-focused. However, use cases require a user-centered perspective, defining *how* actors interact with the system to achieve specific goals. Bridging this gap required breaking down abstract requirements into actionable user flows.

**How it was addressed:**  
Each functional requirement was mapped to one or more use cases by identifying the primary actor involved and the goal they are trying to achieve. For example, "real-time monitoring" was translated into use cases such as *Monitor Patients* and *View Patient Vitals*. This ensured that every requirement was represented as a meaningful interaction within the system.

---

## 2. Granularity of Use Cases

**The challenge:**  
Determining the appropriate level of detail for use cases was difficult. Some initial use cases were too broad (e.g., "Manage System"), making it hard to define clear steps and test cases. Others risked being too detailed, which would overcomplicate the model and reduce clarity.

**How it was addressed:**  
Use cases were refined to represent single, goal-oriented interactions. Broad functionalities were decomposed into smaller, manageable use cases such as *Manage Users* and *Configure System*. This balance ensured that each use case remained testable while still aligning with the overall system scope.

---

## 3. Mapping Stakeholders to Use Cases

**The challenge:**  
In Assignment 4, stakeholders were defined with specific concerns and goals. Ensuring that these concerns were reflected in the use cases required careful alignment. Some stakeholders, such as the Compliance Officer or IT Administrator, do not directly interact with the system in obvious ways, making their representation less straightforward.

**How it was addressed:**  
Actors in the use case model were derived from stakeholders, and their interactions were mapped to relevant system functionality. Indirect stakeholder concerns (such as security and performance) were incorporated into use case constraints and non-functional test scenarios rather than explicit user interactions. This ensured all stakeholder needs were still addressed.

---

## 4. Deriving Test Cases from Use Cases

**The challenge:**  
While use cases describe system behavior, test cases require precise, measurable steps and expected outcomes. Translating narrative flows into structured test cases required additional refinement to ensure clarity and testability.

**How it was addressed:**  
Each use case was used as a basis for one or more test cases. The basic flow of the use case informed the test steps, while the postconditions defined the expected results. This created a clear traceability between requirements, use cases, and test cases, ensuring that all functionality could be validated systematically.

---

## 5. Testing Non-Functional Requirements

**The challenge:**  
Non-functional requirements such as performance, security, and scalability are not tied to a single user interaction, making them harder to translate into test scenarios. Unlike functional tests, they require simulation and measurable thresholds.

**How it was addressed:**  
Non-functional requirements were converted into scenario-based tests with defined metrics. For example, performance requirements were tested by simulating concurrent users and measuring response times, while security requirements were validated through unauthorized access attempts. This ensured that non-functional aspects of the system were verifiable.

---

## 6. Maintaining Consistency Across Artifacts

**The challenge:**  
Ensuring consistency between requirements (Assignment 4), use cases, and test cases was critical. Any mismatch could result in gaps where certain requirements were not represented or tested.

**How it was addressed:**  
A traceability approach was used, linking functional requirements to use cases and test cases. This ensured that every requirement defined earlier was accounted for in both system interactions and validation processes, improving overall coherence and completeness.

---

## Key Lesson

The most important lesson from this process is that requirements alone do not guarantee a well-defined system. They must be translated into clear user interactions and validated through structured testing. This transformation highlights the importance of traceability, clarity, and iterative refinement in software engineering. It also demonstrates that effective system design requires not only understanding stakeholder needs, but also ensuring those needs can be practically implemented and verified.
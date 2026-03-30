# Use Case Specifications

---

## Use Case 1: Record Patient Vitals

**Actor:** Nurse  
**Precondition:** Nurse is logged into the system  
**Postcondition:** Patient vital data is stored successfully  

### Basic Flow:
1. Nurse logs into the system  
2. Nurse selects a patient from the dashboard  
3. Nurse enters vital readings (heart rate, blood pressure, temperature)  
4. System validates the input values  
5. System saves the data to the database  
6. System confirms successful recording  

### Alternative Flows:
- If input values are invalid → system displays validation error  
- If database save fails → system displays error message  

---

## Use Case 2: View Patient Vitals

**Actor:** Doctor  
**Precondition:** Doctor is logged into the system  
**Postcondition:** Patient vitals are displayed  

### Basic Flow:
1. Doctor logs into the system  
2. Doctor selects a patient  
3. System retrieves patient data  
4. System displays real-time vitals  

### Alternative Flows:
- If patient is not found → system displays error  
- If data retrieval fails → system shows error message  

---

## Use Case 3: Receive Alert

**Actor:** Doctor  
**Precondition:** System is actively monitoring patient vitals  
**Postcondition:** Doctor is notified of abnormal condition  

### Basic Flow:
1. System detects abnormal vital readings  
2. System generates an alert  
3. System sends notification to doctor  
4. Doctor views alert details  

### Alternative Flows:
- If notification fails → system logs error and retries  

---

## Use Case 4: Trigger Alert

**Actor:** Nurse  
**Precondition:** Nurse is logged into the system  
**Postcondition:** Alert is generated and sent  

### Basic Flow:
1. Nurse observes abnormal patient condition  
2. Nurse manually triggers alert  
3. System processes alert  
4. System notifies doctor  

### Alternative Flows:
- If system fails → alert not sent and error displayed  

---

## Use Case 5: Monitor Patients

**Actor:** Nurse  
**Precondition:** Nurse is logged into the system  
**Postcondition:** Patient data is displayed continuously  

### Basic Flow:
1. Nurse opens monitoring dashboard  
2. System displays all patients in ward  
3. System updates vital data in real-time  

### Alternative Flows:
- If connection fails → system shows offline status  

---

## Use Case 6: Manage Users

**Actor:** Admin  
**Precondition:** Admin is logged into the system  
**Postcondition:** User account is created, updated, or deleted  

### Basic Flow:
1. Admin accesses user management panel  
2. Admin selects action (add, update, delete)  
3. System processes request  
4. System confirms action  

### Alternative Flows:
- If invalid user data → system displays error  

---

## Use Case 7: Configure System

**Actor:** Admin  
**Precondition:** Admin is logged into the system  
**Postcondition:** System settings are updated  

### Basic Flow:
1. Admin opens configuration settings  
2. Admin modifies thresholds or parameters  
3. System validates changes  
4. System saves new configuration  

### Alternative Flows:
- If invalid configuration → system displays error  

---

## Use Case 8: Review Patient History

**Actor:** Doctor  
**Precondition:** Doctor is logged into the system  
**Postcondition:** Patient history is displayed  

### Basic Flow:
1. Doctor selects patient  
2. Doctor requests history  
3. System retrieves historical data  
4. System displays patient history  

### Alternative Flows:
- If no history found → system displays message  
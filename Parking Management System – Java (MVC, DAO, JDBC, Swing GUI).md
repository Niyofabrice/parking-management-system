## **Parking Management System – Java (MVC, DAO, JDBC, Swing GUI)**

## ---

# **Short Description**

Parking Management System is a Java application designed to manage vehicle parking operations in environments such as a mall, office building, or apartment complex. The system allows the operator to register vehicles, assign parking slots, track parking duration, and manage checkin/checkout processes. It is built using Java Swing for the user interface, JDBC for database communication, and follows the MVC and DAO design for code organization, scalability, and maintainability. The application enforces business and technical validation rules for clean data entry and reliable system behavior.

## ---

# **Full Project Requirements**

## **1\. System Overview**

The system manages vehicle parking activities, including registering vehicles, managing parking slots, recording time-in/time-out, and generating logs. Users interact with the system through a multi-page graphical interface, and data is stored in a relational database(PostgresSQL) using JDBC.

---

# **2\. Functional Requirements**

### **2.1. User Management**

* The system shall allow users to log in using a valid username and password.

* The system shall validate login credentials against stored database records.

* The system shall restrict access to the system unless valid credentials are provided.

---

### **2.2. Vehicle Registration**

* The system shall allow the user to register a vehicle by entering plate number, owner name, phone number, and vehicle type.

* The system shall allow the operator to perform CRUD operations on vehicle records.

* The system shall display registered vehicles in a JTable.

* The system shall prevent duplicate vehicle plate numbers from being added.

---

### **2.3. Parking Slot Management**

* The system shall allow the user to create and manage parking slots with attributes such as slot number, slot type, occupancy status, and floor number.

* The system shall prevent assigning an occupied slot to another vehicle.

* The system shall update slot status (occupied/free) automatically during check-in/check-out.

* The system shall display all parking slots in a JTable.

---

### **2.4. Parking Operations (Check-In / Check-Out)**

* The system shall allow a vehicle to be checked in by selecting it and assigning a free parking slot.

* The system shall record time-in automatically during check-in.

* The system shall record time-out during check-out.

* The system shall prevent a vehicle from checking in twice without checking out.

* The system shall show appropriate success or error messages using JOptionPane.

* The system shall store all parking sessions in a parking\_record table.

---

### **2.5. Reporting**

* The system shall display a list of completed parking sessions (history) in a JTable.

* The system may optionally generate daily or monthly logs.

---

# **3\. Non-Functional Requirements**

### **3.1. Usability**

* The system’s Swing GUI shall contain a minimum of four fully navigable pages:

  * Login Page

  * Dashboard

  * Vehicle Management

  * Parking Slot & Parking Operations

* The UI shall be user-friendly, clear, and visually appealing.

* All forms shall provide appropriate error and information messages using JOptionPane.

---

### **3.2. Performance**

* The system shall store and retrieve data efficiently from the database using JDBC.

* CRUD operations should execute within 1–2 seconds.

* The system’s JTable views should update immediately after database changes.

---

### **3.3. Reliability**

* The system must enforce all business and technical rules to prevent incorrect data entry.

* The system shall display validation warnings if any required fields are empty or invalid.

* The system should protect against duplicate records through database constraints.

---

### **3.4. Maintainability**

* The system must follow the **MVC architecture** to ensure separation of concerns.

* The system must implement the **DAO pattern** for all database operations.

* The code should be modular, allowing easy updates or extensions.

---

### **3.5. Security**

* Login credentials shall be validated against the database.

* Passwords shall be stored securely.

* Unauthorized users shall not access the system.

---

# **4\. Validation Rules**

### **Business Validation Rules**

1. A vehicle cannot be checked in if it is already currently parked.

2. A parking slot cannot be assigned if it is occupied.

3. Plate numbers must be unique.

4. Time-out must be greater than time-in.

5. Vehicle type must match slot type.

---

### **Technical Validation Rules**

1. Plate number field must not be empty.

2. Phone number must contain exactly 10 digits.

3. Slot number must be unique.

4. Username must be unique and not null.

5. Time-in must be automatically generated (not null).

---

# **5\. Technologies Used**

* **Java SE**

* **Java Swing** (GUI)

* **JDBC API**

* **MVC \+ DAO pattern**

* **PostgreSQL database**

* **JTable** for data display  

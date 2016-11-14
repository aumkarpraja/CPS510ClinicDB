CREATE TABLE Employees
(
  emp_id NUMBER PRIMARY KEY,
  emp_name varchar2(255) NOT NULL,
  emp_role varchar2(255) NOT NULL,
  emp_salary NUMBER NOT NULL,
  address_id NUMBER NOT NULL,
  FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE Patients
(
 pat_id NUMBER NOT NULL PRIMARY KEY,
 pat_name VARCHAR2(255) NOT NULL,
 pat_dob varchar(20) NOT NULL,
 pat_gender varchar2(4) NOT NULL,
 address_id NUMBER NOT NULL,
  FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE Address
(
 address_id NUMBER NOT NULL PRIMARY KEY,
 address VARCHAR2(255) NOT NULL,
 city VARCHAR2(255) NOT NULL,
 phonenum NUMBER NOT NULL,
 postal VARCHAR2(255) NOT NULL
);

CREATE TABLE Patient_Medical_Detail
(
 ohip NUMBER PRIMARY KEY,
 pat_bloodtype VARCHAR2(255),
 pat_allergies VARCHAR2(255),
 pat_id NUMBER NOT NULL,
 FOREIGN KEY (pat_id) REFERENCES patients(pat_id)
);

CREATE TABLE Appointment
(
visit_id NUMBER PRIMARY KEY,
visit_time TIMESTAMP NOT NULL,
visit_cost NUMBER NOT NULL,
visit_reason VARCHAR2(255) NOT NULL,
pat_id NUMBER NOT NULL,
emp_id NUMBER NOT NULL,
FOREIGN KEY (pat_id) REFERENCES patients(pat_id),
FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

CREATE TABLE VISIT_PRESCRIPTION
  (
    prescription_id NUMBER PRIMARY KEY,
    prescription_amount VARCHAR2(255) NOT NULL,
    prescription_cost NUMBER NOT NULL,
    visit_id NUMBER NOT NULL,
    med_id NUMBER NOT NULL,
    FOREIGN KEY (visit_id) REFERENCES appointment(visit_id),
    FOREIGN KEY (med_id) REFERENCES medications(med_id)
  );
  
CREATE TABLE MEDICATIONS
(
 med_id NUMBER PRIMARY KEY,
 med_name varchar2(255) NOT NULL,
 med_base_cost NUMBER NOT NULL,
 med_use VARCHAR2(255) NOT NULL
);
  
CREATE TABLE VISI_PROCEDURE
(
  procedure_id NUMBER PRIMARY KEY,
  procedure_cost NUMBER NOT NULL,
  visit_id NUMBER NOT NULL,
  med_procedure_id NUMBER NOT NULL,
  FOREIGN KEY (visit_id) REFERENCES appointment(visit_id),
  FOREIGN KEY (med_procedure_id) REFERENCES medical_procedure(med_procedure_id)
);

CREATE TABLE MEDICAL_PROCEDURE
(
  med_procedure_id NUMBER PRIMARY KEY,
  procedure_name VARCHAR2(255) NOT NULL,
  procedure_base_cost NUMBER NOT NULL
);
  

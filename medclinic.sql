CREATE TABLE Employees
(
  e_id NUMBER PRIMARY KEY,
  lastname varchar(255) NOT NULL,
  firstname varchar(255) NOT NULL,
  employeesalary FLOAT(2) NOT NULL,
  employeerole varchar(255) NOT NULL
);

CREATE TABLE Patients
(
 lastcheckup DATE,
 p_id NUMBER NOT NULL PRIMARY KEY,
 p_name NUMBER NOT NULL,
 healthcard_num int NOT NULL,
 p_address VARCHAR2(255) NOT NULL,
 p_number VARCHAR2(12) NOT NULL,
 dob varchar(11) NOT NULL,
 bloodtype varchar(4) NOT NULL,
 allergies varchar(512)
);

CREATE TABLE Appointment
(
p_id NUMBER NOT NULL,
e_id NUMBER NOT NULL,
reason varchar(1024) NOT NULL,
appointmentime TIMESTAMP NOT NULL,
appointment_id NUMBER NOT NULL PRIMARY KEY,
FOREIGN KEY (p_id) REFERENCES patients (p_id),
FOREIGN KEY (e_id) REFERENCES employees (e_id)
);

CREATE TABLE PharmacistData
(
 p_id NUMBER NOT NULL,
 e_id NUMBER NOT NULL,
 prescription varchar(1024) NOT NULL,
 prescription_id NUMBER NOT NULL PRIMARY KEY,
 FOREIGN KEY (p_id) REFERENCES patients (p_id),
 FOREIGN KEY (e_id) REFERENCES employees (e_id)
);

CREATE TABLE TestResults
(
 p_id NUMBER NOT NULL,
 e_id NUMBER NOT NULL,
 testtype varchar(255)NOT NULL,
 testresult varchar(1024) NOT NULL,
 test_id NUMBER NOT NULL PRIMARY KEY,
 FOREIGN KEY (p_id) REFERENCES patients (p_id),
 FOREIGN KEY (e_id) REFERENCES employees (e_id)
);

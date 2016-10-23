CREATE TABLE Employees
(
  e_id VARCHAR2(20) NOT NULL PRIMARY KEY,
  lastname varchar(255) NOT NULL,
  firstname varchar(255) NOT NULL,
  employeesalary FLOAT(2) NOT NULL,
  employeerole varchar(255) NOT NULL,
);

CREATE TABLE Patients
(
 lastcheckup DATE,
 p_id VARCHAR2(20) NOT NULL PRIMARY KEY,
 p_name VARCHAR(30) NOT NULL,
 healthcard_num int NOT NULL,
 p_address VARCHAR2(255) NOT NULL,
 p_number VARCHAR2(12) NOT NULL,
 dob varchar(11) NOT NULL, 
 bloodtype varchar(4) NOT NULL,
 allergies varchar(512)
);

CREATE TABLE Appointment
(
p_id VARCHAR2(20) NOT NULL,
e_id VARCHAR2(20) NOT NULL,
reason varchar(1024) NOT NULL PRIMARY KEY,
appointmentaime TIMESTAMP,
FOREIGN KEY (p_id) REFERENCES patients (p_id),
FOREIGN KEY (e_id) REFERENCES employees (e_id)
);

CREATE TABLE PharmacistData
(
 p_id VARCHAR2(20) NOT NULL,
 e_id VARCHAR2(20) NOT NULL,
 medicineperscribed varchar(1024) NOT NULL PRIMARY KEY,
 FOREIGN KEY (p_id) REFERENCES patients (p_id),
 FOREIGN KEY (e_id) REFERENCES employees (e_id)
);

CREATE TABLE TestResults
(
 p_id VARCHAR2(20) NOT NULL,
 e_id VARCHAR2(20) NOT NULL,
 testtype varchar(255)NOT NULL PRIMARY KEY,
 testresult varchar(1024) NOT NULL,
 FOREIGN KEY (p_id) REFERENCES patients (p_id),
 FOREIGN KEY (e_id) REFERENCES employees (e_id)
);

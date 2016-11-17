#!/bin/sh

export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib

user=""
pass=""

set linesize 600;

echo "1. QUERIES"
echo "2. DROP all tables"
echo "3. CREATE all tables"
echo "4. POPULATE all tables"

read option

if [[ $option == "1" ]]; then
echo "1: See the appointment and prescription required given by a certain doctor."
echo "2: Determine number of employees"
echo "3: Determine average salary"
echo "4: Obtain the prescription ID for the medicine and for which patient it's needed"
echo "5: Get the length of patient's address"
echo "SELECT a query:"
read numberSelect
fi

if [[ $option == "2" ]]; then
sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
	    DROP TABLE APPOINTMENT;
	    DROP TABLE PHARMACISTDATA;
	    DROP TABLE TESTRESULTS;
	    DROP TABLE PATIENTS;
	    DROP TABLE EMPLOYEES;
EOF
fi
if [[ $option == "3" ]]; then
sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
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
 		p_id NUMBER NOT NULL PRIMARY KEY,
 		p_fname VARCHAR2(255) NOT NULL,
 		p_lname VARCHAR2(255) NOT NULL,
 		healthcard_num int NOT NULL,
 		p_address VARCHAR2(255) NOT NULL,
 		p_number VARCHAR2(12) NOT NULL,
 		dob varchar(11) NOT NULL,
 		bloodtype varchar(4) NOT NULL
	);
	
	CREATE TABLE Appointment
	(
		p_id NUMBER NOT NULL,
		e_id NUMBER NOT NULL,
		reason varchar(1024) NOT NULL,
		appointmenttime TIMESTAMP NOT NULL,
		appointment_id NUMBER NOT NULL PRIMARY KEY,
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

	
	 CREATE TABLE PharmacistData
	(
 		p_id NUMBER NOT NULL,
 		e_id NUMBER NOT NULL,
 		prescription varchar(1024) NOT NULL,
 		prescription_id NUMBER NOT NULL PRIMARY KEY,
 		FOREIGN KEY (p_id) REFERENCES patients (p_id),
 		FOREIGN KEY (e_id) REFERENCES employees (e_id)
	);
EOF
fi

if [[ $option == "4" ]]; then
	sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (1, 'doctor', 100000, 'Michael', 'Scott');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (2, 'doctor', 100000, 'Amanda', 'Sherwyn');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (3, 'doctor', 100000, 'Sandy', 'Be');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (4, 'pharmacist', 100000, 'Andy', 'Smith');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (5, 'pharmacist', 100000, 'Jason', 'Bran');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (6, 'pharmacist', 100000, 'Samuel', 'Watson');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (7, 'nurse', 70000, 'Calvin', 'Moreno');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (8, 'nurse', 80000, 'Brandon', 'Graham');
	INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (9, 'nurse', 75000, 'Julia', 'Shoes');
	INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (1, 'Mary', 'Doe', 123123123, '76 Claremont St', '4161111111', '06/17/90', 'O+');
	INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (2, 'Marie', 'Do', 123321321, '7 Claremont St', '4162221111', '01/17/92', 'O-');
	INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (3, 'Marnie', 'De', 123132123, '176 Claremont St', '4163331111', '02/17/94', 'O+');
	INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (4, 'Mar', 'Soe', 123123321, '376 Claremont St', '4164441111', '12/17/92', 'A+');
	INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (5, 'Varus', 'Uno', 123123213, '176 Claremont St', '4165551111', '10/17/92', 'B+');
	INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (6, 'Mort', 'Bone', 123123312, '476 Claremont St', '4166661111', '03/17/93', 'A-');
	INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (1,3, 'Wisdom tooth gum infection', (TO_DATE('2016/05/03 11:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130000);
	INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (3,3, 'Wisdom tooth minor gum infection', (TO_DATE('2016/05/03 12:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130001);
	INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (4,2, 'Strep throat', (TO_DATE('2016/05/03 11:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130002);
	INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (5,2, 'Nausea', (TO_DATE('2016/05/03 12:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130003);
	INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (6,1, 'Acne', (TO_DATE('2016/05/03 13:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130004);
	INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (2,1, 'Ear ringing', (TO_DATE('2016/05/03 14:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130005);
	INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (4, 1,'Amoxicillin 250mg tabs', 500500501);
	INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (4, 3,'Amoxicillin 50mg tabs', 500500502);
	INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (5, 4,'Clindymacin 250mg cap', 500500503);
	INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (5, 5,'Zofran 4mg tabs', 500500504);
	INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (6, 6,'Bezaclin 250mg cap', 500500505);
	INSERT INTO TESTRESULTS(E_ID , P_ID , TESTRESULT , TESTTYPE, TEST_ID) VALUES (2,4, 'positive','strepthroat', 200200200);
EOF
fi
if [[ $option == "1" ]]; then
	if [[ $numberSelect == "1" ]]; then
	 	sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
	    SELECT appointment.appointment_id, appointment.e_id,appointment.p_id, pharmacistdata.prescription FROM appointment INNER JOIN pharmacistdata on appointment.p_id=pharmacistdata.p_id;
EOF
	elif [[ $numberSelect == "2" ]]; then
	    sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
	    SELECT COUNT(EMPLOYEEROLE) FROM EMPLOYEES;
EOF
	elif [[ $numberSelect == 3 ]]; then
	    sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
	   SELECT AVG(EMPLOYEESALARY) FROM EMPLOYEES;
EOF
	elif [[ $numberSelect == 4 ]]; then
	    sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
	   SELECT DISTINCT appointment.p_id, pharmacistdata.prescription_id, pharmacistdata.prescription FROM appointment INNER JOIN pharmacistdata on appointment.p_id=pharmacistdata.p_id;
EOF
	elif [[ $numberSelect == 5 ]]; then
	    sqlplus64 "$user/$pass@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=141.117.57.159)(Port=1521))(CONNECT_DATA=(SID=orcl)))"  <<EOF
        SELECT p_fname,LENGTH(p_address) as LengthOfAddress FROM patients;
EOF
	fi
fi





exit;

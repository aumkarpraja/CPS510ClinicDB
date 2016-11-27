import java.sql.*;
/**
 * This class is responsible for communicating with the SQL database
 * and making updates to it as well as retreiving from it. The SparkJava
 * API uses these functions.
 */

public class Database {
    // Driver info
    static Connection con = null;
    static Statement st = null;
    static ResultSet rs = null;

    public Database() {
        // Connect to the database when an object is created.
        connect();
    }

    /**
     * Connects to the database.
     */
    void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Connecting to database...");

        // Make a connection to the DB, this is currently set up with port tunneling in mind
        try {
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "c54wang", "06180404"); // @TODO: NEEDS TO BE CHANGED
            st = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected!");
        System.out.println("");
    }

    /**
     * Gets employees table and converts it into a neat table...neat!
     * @return formatted query of employees table
     */
    String getEmployees() {
        String result = "";
        result += "<h1>Employees</h1>";
        result += "<table border='1'><tr><th>emp_id</th><th>emp_name</th>><th>emp_role</th><th>emp_salary</th><th>address_id</th></tr>";

        try {
            rs = st.executeQuery("SELECT * FROM EMPLOYEES");

            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets patients table, formats in a HTML table
     * @return formatted query of patients table
     */
    String getPatients() {
        String result = "";
        result += "<h1>Patients</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM PATIENTS");
            result += "<table border='1'><tr><th>pat_id</th><th>pat_name</th><th>pat_dob</th><th>pat_gender</th><th>address_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets patients table, formats in an HTML table
     * @return formatted query of patients table
     */
    String getAddress() {
        String result = "";
        result += "<h1>Address</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Address");
            result += "<table border='1'><tr><th>address_id</th><th>address</th><th>city</th><th>phonenum</th><th>postal</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets patients medical details table, formats in an HTML table
     * @return formatted query of patients medical detail table
     */
    String getPatientMedicalDetail() {
        String result = "";
        result += "<h1>Patient_Medical_Detail</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Patient_Medical_Detail");
            result += "<table border='1'><tr><th>ohip</th><th>pat_bloodtype</th><th>pat_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 3; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";

        return result;
    }

    /**
     * Gets appointments table, formats in an HTML table
     * @return formatted query of appointment table
     */
    String getAppointment() {
        String result = "";
        result += "<h1>Appointment</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Appointment");
            result += "<table border='1'><tr><th>visit_id</th><th>visit_time</th><th>visit_cost</th><th>visit_reason</th><th>pat_id</th><th>emp_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 6; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets vistiation prescription table, formats in an HTML table
     * @return formatted query of visit prescription table
     */
    String getVisitPrescription() {
        String result = "";
        result += "<h1>Visit_Prescription</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Visit_Prescription");
            result += "<table border='1'><tr><th>prescription_id</th><th>prescription_amount</th><th>prescription_cost</th><th>visit_id</th><th>med_id</th>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets medication table, formats in an HTML table
     * @return formatted query of medications table
     */
    String getMedications() {
        String result = "";
        result += "<h1>Medications</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Medications");
            result += "<table border='1'><tr><th>med_id</th><th>med_name</th><th>med_base_cost</th><th>med_use</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 4; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets procedures table, formats in an HTML table
     * @return formatted query of procedures table
     */
    String getVisitProcedure() {
        String result = "";
        result += "<h1>Visit_Procedure</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Visit_Procedure");
            result += "<table border='1'><tr><th>procedure_id</th><th>procedure_cost</th><th>visit_id</th><th>med_procedure_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 4; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    /**
     * Gets procedures table, formats in an HTML table
     * @return formatted query of procedures table
     */
    String getMedicalProcedure() {
        String result = "";
        result += "<h1>Medical_Procedure</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Medical_Procedure");
            result += "<table border='1'><tr><th>med_procedure_id</th><th>procedure_name</th><th>procedure_base_cost</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 3; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }


    /**
     * Part of the debug functions to create all tables.
     * @return complete if successful or an error.
     */
    String createAll() {
        try {
            st.executeUpdate("CREATE TABLE Address" +
                    "(" +
                    "address_id NUMBER NOT NULL PRIMARY KEY," +
                    "address VARCHAR2(255) NOT NULL," +
                    "city VARCHAR2(255) NOT NULL," +
                    "phonenum NUMBER NOT NULL," +
                    "postal VARCHAR2(255) NOT NULL)");

            st.executeUpdate("CREATE TABLE Employees" +
                    "(" +
                    "emp_id NUMBER PRIMARY KEY," +
                    "emp_name varchar2(255) NOT NULL," +
                    "emp_role varchar2(255) NOT NULL," +
                    "emp_salary NUMBER NOT NULL," +
                    "address_id NUMBER NOT NULL," +
                    "FOREIGN KEY (address_id) REFERENCES address(address_id))");

            st.executeUpdate("CREATE TABLE Patients" +
                    "(" +
                    "pat_id NUMBER NOT NULL PRIMARY KEY," +
                    "pat_name VARCHAR2(255) NOT NULL," +
                    "pat_dob varchar(20) NOT NULL," +
                    "pat_gender varchar2(4) NOT NULL," +
                    "address_id NUMBER NOT NULL," +
                    "FOREIGN KEY (address_id) REFERENCES address(address_id))");

            st.executeUpdate("CREATE TABLE Patient_Medical_Detail" +
                    "(" +
                    "ohip NUMBER PRIMARY KEY," +
                    "pat_bloodtype VARCHAR2(255)," +
                    "pat_id NUMBER NOT NULL," +
                    "FOREIGN KEY (pat_id) REFERENCES patients(pat_id))");

            st.executeUpdate("CREATE TABLE Appointment" +
                    "(" +
                    "visit_id NUMBER PRIMARY KEY," +
                    "visit_time TIMESTAMP NOT NULL," +
                    "visit_cost NUMBER NOT NULL," +
                    "visit_reason VARCHAR2(255) NOT NULL," +
                    "pat_id NUMBER NOT NULL," +
                    "emp_id NUMBER NOT NULL," +
                    "FOREIGN KEY (pat_id) REFERENCES patients(pat_id)," +
                    "FOREIGN KEY (emp_id) REFERENCES employees(emp_id))");

            st.executeUpdate("CREATE TABLE Medications" +
                    "(" +
                    "med_id NUMBER PRIMARY KEY," +
                    " med_name varchar2(255) NOT NULL," +
                    " med_base_cost NUMBER NOT NULL," +
                    " med_use VARCHAR2(255) NOT NULL)");

            st.executeUpdate("CREATE TABLE Visit_Prescription" +
                    "(" +
                    "prescription_id NUMBER PRIMARY KEY," +
                    "prescription_amount NUMBER NOT NULL," +
                    "prescription_cost NUMBER NOT NULL," +
                    "visit_id NUMBER NOT NULL," +
                    "med_id NUMBER NOT NULL," +
                    "FOREIGN KEY (visit_id) REFERENCES appointment(visit_id)," +
                    "FOREIGN KEY (med_id) REFERENCES medications(med_id))");

            st.executeUpdate("CREATE TABLE Medical_Procedure" +
                    "(" +
                    "med_procedure_id NUMBER PRIMARY KEY," +
                    "procedure_name VARCHAR2(255) NOT NULL," +
                    "procedure_base_cost NUMBER NOT NULL)");

            st.executeUpdate("CREATE TABLE Visit_Procedure" +
                    "(" +
                    "procedure_id NUMBER PRIMARY KEY," +
                    "procedure_cost NUMBER NOT NULL," +
                    "visit_id NUMBER NOT NULL," +
                    "med_procedure_id NUMBER NOT NULL," +
                    "FOREIGN KEY (visit_id) REFERENCES appointment(visit_id)," +
                    "FOREIGN KEY (med_procedure_id) REFERENCES medical_procedure(med_procedure_id))");


        } catch (SQLException e) {
            e.printStackTrace();
            return "Unable to create all tables. They already exist or no connection to the DB could be made.";
        }
        return "<h1>Creating Tables Complete</h1>";
    }

    /**
     * Drop all tables
     * @return
     */
    String dropAll() {
        try {
            rs = st.executeQuery("DROP TABLE VISIT_PROCEDURE");
            rs = st.executeQuery("DROP TABLE MEDICAL_PROCEDURE");
            rs = st.executeQuery("DROP TABLE VISIT_PRESCRIPTION");
            rs = st.executeQuery("DROP TABLE MEDICATIONS");
            rs = st.executeQuery("DROP TABLE APPOINTMENT");
            rs = st.executeQuery("DROP TABLE PATIENT_MEDICAL_DETAIL");
            rs = st.executeQuery("DROP TABLE PATIENTS");
            rs = st.executeQuery("DROP TABLE EMPLOYEES");
            rs = st.executeQuery("DROP TABLE ADDRESS");

        } catch (SQLException e) {
            e.printStackTrace();
            return "<h1>Unable to drop all tables</h1>";
        }
        return "<h1>Dropping All Complete</h1>";
    }

    /**
     * Populate all tables.
     * @return
     */
    String popAll() {
        try {
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('10 Potato St', 1, 'Toronto',1231231234, 'm1q2w3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('13 Potato St', 2, 'Toronto',1231231111, 'm1q2a3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('16 Potato St', 3, 'Toronto',1231232222, 'm1q2b3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('18 Potato St', 4, 'Toronto',1231234444, 'm1q2c3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('21 Potato St', 5, 'Toronto',1231233333, 'm1q2d3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('232 Potato St', 6, 'Toronto',1231235555, 'm1q2e3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('233 Potato St', 7, 'Toronto',1231237777, 'm1q2f3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('700 Potato St', 8, 'Toronto',1231239999, 'm1q2g3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('233 Potato St', 9, 'Toronto',1231241111, 'm1q2f3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('701 Potato St', 10, 'Toronto',1231262122, 'm1q2m3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('702 Potato St', 11, 'Toronto',1231252231, 'm1q2m3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('703 Potato St', 12, 'Toronto',1231242265, 'm1q2m7')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('733 Potato St', 13, 'Toronto',1231242456, 'm1q2m6')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('744 Potato St', 14, 'Toronto',1231242552, 'm1q2m5')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('705 Potato St', 15, 'Toronto',1231242122, 'm1q2m4')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('707 Potato St', 16, 'Toronto',1231242622, 'm1q2m3')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('709 Potato St', 17, 'Toronto',1231242992, 'm1q2m2')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('899 Potato St', 18, 'Toronto',1231242277, 'm1q2m1')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('96 Potato St', 19, 'Toronto',1231242231, 'm1q2k2')");
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES ('7 Potato St', 20, 'Toronto',1231241111, 'm1q2s1')");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (1,1, 'George Potatoer', 'doctor', 150000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (2,2, 'Jane Potating', 'doctor', 100000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (3,3, 'Annie Potatoes', 'pharmacist', 120000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (4,4, 'Bill Taters', 'nurse', 110000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (9,5, 'Billa Tateings', 'nurse', 110000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (10,6, 'Jill Tates', 'nurse', 110000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (11,7, 'Ann Potatoer', 'nurse', 120000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (12,8, 'Annie Potatotes', 'pharmacist', 120000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (13,9, 'Annie Potatoing', 'pharmacist', 110000)");
            st.executeUpdate("INSERT INTO EMPLOYEES(ADDRESS_ID , EMP_ID , EMP_NAME , EMP_ROLE , EMP_SALARY) VALUES (14,10, 'Annie Potatate', 'pharmacist', 100000)");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (5, 'June 1, 1990', 'M', 1, 'Joe Spud')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (6, 'June 3, 1980', 'F', 2, 'Abby Spud')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (7, 'June 10, 1960', 'F', 3, 'Lisa Spudster')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (8, 'July 1, 1995', 'M', 4, 'Jack Spudster')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (15, 'July 1, 1995', 'M', 5, 'Jack Spudstersingers')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (16, 'July 1, 1990', 'F', 6, 'Jackie Spudster')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (17, 'July 1, 1965', 'F', 7, 'Jacky Spudsterings')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (18, 'July 1, 1935', 'F', 8, 'Jack Spuds')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (19, 'July 1, 1975', 'M', 9, 'Potate Sci')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES (20, 'July 1, 1945', 'M', 10, 'Eye Spud')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(1, 111222333, 'O+')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(2, 111222444, 'O-')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(3, 111222555, 'B+')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(4, 111222666, 'AB+')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(5, 111222777, 'B+')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(6, 111222888, 'O+')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(7, 111222999,'B+')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(8, 111333666,'A-')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(9, 111444555, 'B-')");
            st.executeUpdate("INSERT INTO PATIENT_MEDICAL_DETAIL(PAT_ID, OHIP , PAT_BLOODTYPE) VALUES(10, 111555666, 'A+')");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (1,4,30,1,'Acne', (TO_DATE('2016/05/03 12:10:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (2,1,50,2,'Sore Throat', (TO_DATE('2016/05/03 12:10:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (2,3,0,3,'Annual check up', (TO_DATE('2016/05/03 13:10:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (1,5,0,4,'Annual check up', (TO_DATE('2016/05/03 13:10:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (3,6,0,5,'Acne', (TO_DATE('2016/05/03 13:30:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (4,7,0,6,'Annual check up', (TO_DATE('2016/05/04 14:10:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (5,8,0,7,'Fractured Arm', (TO_DATE('2016/05/05 12:10:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (6,9,0,8,'Annual check up', (TO_DATE('2016/05/06 10:00:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (9,10,0,9,'Annual check up', (TO_DATE('2016/05/07 11:15:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (10,2,0,10,'Fractured Leg', (TO_DATE('2016/05/08 12:30:00', 'yyyy/mm/dd hh24:mi:ss')))");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (10, 1, 'Tylenol', 'Aches')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (30, 2, 'Benzaclin', 'Acne')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (40, 3, 'Potatonol', 'Spuds')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (60, 4, 'Zofran', 'Axiety')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (70, 5, 'Clamydicin', 'Skin Inflammation')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (60, 6, 'Acebutolol', 'Lungs')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (30, 7, 'Olopatadine', 'Oral Infection')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (90, 8, 'Oxazepam', 'Alcohol Withdrawal')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (200, 9, 'Icodextrin', 'Peritoneal Dialysis')");
            st.executeUpdate("INSERT INTO MEDICATIONS(MED_BASE_COST , MED_ID , MED_NAME , MED_USE) VALUES (160, 10, 'Thioridazine', 'Psychoses')");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(2, 1, 60,1,1)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(1, 1, 10,2,3)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(3, 1, 40,3,2)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(4, 1, 60,4,4)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(5, 1, 70,5,5)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(6, 1, 60,6,6)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(7, 1, 10,7,7)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(8, 1, 90,8,8)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(9, 1, 200,9,9)");
            st.executeUpdate("INSERT INTO VISIT_PRESCRIPTION(MED_ID , PRESCRIPTION_AMOUNT , PRESCRIPTION_COST , PRESCRIPTION_ID , VISIT_ID) VALUES(10, 1,10,10,10)");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (1, 20, 'Potato swab')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (2, 40, 'Extraction')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (3, 10, 'Flushing')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (4, 260, 'X-Ray')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (5, 10, 'Swabbing')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (6, 90, 'Blood test')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (7, 10, 'Bandaging')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (8, 400, 'Therapy')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (9, 0, 'Consultation')");
            st.executeUpdate("INSERT INTO MEDICAL_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_BASE_COST , PROCEDURE_NAME) VALUES (10, 0, 'BMI measurement')");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (1, 20, 1, 1)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (2, 40, 2, 2)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (3, 10, 3, 3)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (7, 10, 4, 4)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (9, 0, 5, 5)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (7, 10, 6, 6)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (7, 10, 7, 7)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (10, 0, 8, 8)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (4, 260, 9, 9)");
            st.executeUpdate("INSERT INTO VISIT_PROCEDURE(MED_PROCEDURE_ID , PROCEDURE_COST , PROCEDURE_ID , VISIT_ID) VALUES (4, 260, 10, 10)");

        } catch (SQLException e) {
            e.printStackTrace();
            return "<h1>Unable to populate all tables</h1>";
        }
        return "<h1>Populating complete</h1>";
    }

    /**
     * Gets all tables (moreso meant as a debug function)
     * @return
     */
    String getAll() {
        String result = "";
        result += "<h1>Employees</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM EMPLOYEES");
            result += "<table border='1'><tr><th>emp_id</th><th>emp_name</th><th>emp_role</th><th>emp_salary</th><th>address_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";


        result += "<h1>Patients</h1>";

        try {
            rs = st.executeQuery("SELECT * FROM PATIENTS");
            result += "<table border='1'><tr><th>pat_id</th><th>pat_name</th><th>pat_dob</th><th>pat_gender</th><th>address_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";


        result += "<h1>Address</h1>";
        try {

            rs = st.executeQuery("SELECT * FROM ADDRESS");
            result += "<table border='1'><tr><th>address_id</th><th>address</th><th>city</th><th>phonenum</th><th>postal</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>Patient_Medical_Detail</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM PATIENT_MEDICAL_DETAIL");
            result += "<table border='1'><tr><th>ohip</th><th>pat_bloodtype</th><th>pat_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 3; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>Appointment</h1>";
        try {
            rs = st.executeQuery("SELECT * FROM Appointment");
            result += "<table border='1'><tr><th>visit_id</th><th>visit_time</th><th>visit_cost</th><th>visit_reason</th><th>pat_id</th><th>emp_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 6; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";


        result += "<h1>Visit_Prescription</h1>";

        try {
            rs = st.executeQuery("SELECT * FROM Visit_Prescription");
            result += "<table border='1'><tr><th>prescription_id</th><th>prescription_amount</th><th>prescription_cost</th><th>visit_id</th><th>med_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";


        result += "<h1>Medications</h1>";

        try {
            rs = st.executeQuery("SELECT * FROM Medications");
            result += "<table border='1'><tr><th>med_id</th><th>med_name</th><th>med_base_cost</th><th>med_use</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 4; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";


        result += "<h1>Visit_Procedure</h1>";

        try {
            rs = st.executeQuery("SELECT * FROM Visit_Procedure");
            result += "<table border='1'><tr><th>procedure_id</th><th>procedure_cost</th><th>visit_id</th><th>med_procedure_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 4; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>Medical_Procedure</h1>";

        try {
            rs = st.executeQuery("SELECT * FROM medical_procedure");
            result += "<table border='1'><tr><th>med_procedure_id</th><th>procedure_name</th><th>procedure_base_cost</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 3; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result += "</table>";


        return result;
    }


    String advQuery1() {
        String result = "";
        result += "<table border='1'><tr><th>Avg(emp_salary)</th></tr>";

        try {
            rs = st.executeQuery("SELECT AVG(emp_salary) FROM EMPLOYEES");

            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 1; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery2() {
        String result = "";
        result += "<table border='1'><tr><th>visit_reason</th><th>visit_cost</th><th>pat_id</th><th>pat_name</th></tr>";

        try {
            rs = st.executeQuery("SELECT appointment.visit_reason, appointment.visit_cost, appointment.pat_id, patients.pat_name FROM APPOINTMENT INNER JOIN patients ON appointment.pat_id=patients.pat_id");

            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 4; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery3() {
        String result = "";
        result += "<table border='1'><tr><th>Count(med_id)</th></tr>";

        try {
            rs = st.executeQuery("SELECT COUNT(med_id) FROM MEDICATIONS");

            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 1; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery4() {
        String result = "";
        result += "<table border='1'><tr><th>visit_id</th><th>procedure_cost</th><th>procedure_name</th></tr>";

        try {
            rs = st.executeQuery("SELECT DISTINCT visit_procedure.visit_id, visit_procedure.procedure_cost, medical_procedure.procedure_name FROM visit_procedure INNER JOIN medical_procedure on visit_procedure.MED_PROCEDURE_ID=medical_procedure.MED_PROCEDURE_ID");

            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 3; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery5() {
        String result = "";
        result += "<table border='1'><tr><th>postal</th><th>address_length</th></tr>";

        try {
            rs = st.executeQuery("SELECT postal,LENGTH(address) as Address_Length FROM ADDRESS");

            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 2; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    String searchPatient(String patientName) {
        String result = "";

        try {
            rs = st.executeQuery("SELECT * FROM PATIENTS WHERE pat_name LIKE '%" + patientName + "%'");
            if (rs == null)
                return "Try again."; // THIS DOESN'T DUCKING WORK
            else
                result += "<table border='1'><tr><th>pat_id</th><th>pa_name</th><th>dob</th><th>gender</th><th>address_id</th></tr>";
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Search for employees
     * @param employeeName the employees name
     * @return
     */
    String searchEmployee(String employeeName) {
        String result = "";
        result += "<table border='1'><tr><th>emp_id</th><th>emp_name</th><th>emp_role</th><th>emp_salary</th><th>address_id</th></tr>";
        try {
            rs = st.executeQuery("SELECT * FROM EMPLOYEES WHERE emp_name LIKE '%" + employeeName + "%'");
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    String searchEmployeeRole(String employeeRole) {
        String result = "";
        result += "<table border='1'><tr><th>emp_id</th><th>emp_name</th><th>emp_role</th><th>emp_salary</th><th>address_id</th></tr>";
        try {
            // Execute the query and format the output so it's a table
            rs = st.executeQuery("SELECT * FROM EMPLOYEES WHERE emp_role LIKE '%" + employeeRole + "%'");
            while (rs.next()) {
                result += "<tr>";
                for (int i = 1; i <= 5; i++) {
                    result += "<th>" + rs.getString(i) + "</th>";
                    result += "\t";
                }
                result += "</tr>";
            }
            result += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    String createPat(int pat_id, String pat_name, String pat_dob, String pat_gender, int address_id,
                     String address, String city, long phone_num, String postal_code) {
        try {
            // run the query
            st.executeUpdate("INSERT INTO ADDRESS(ADDRESS , ADDRESS_ID , CITY , PHONENUM , POSTAL) VALUES " +
                    "('" + address + "', " + address_id + ", '" + city + "', " + phone_num + ", '" + postal_code + "')");
            st.executeUpdate("INSERT INTO PATIENTS(ADDRESS_ID , PAT_DOB , PAT_GENDER , PAT_ID , PAT_NAME) VALUES " +
                    "(" + address_id + ", '" + pat_dob + "', '" + pat_gender + "', '" + pat_id + "', '" + pat_name + "')");

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error, unable to add patient. Check that you have entered all info correctly.";
        }
        return "Complete";
    }

    String createAppt(int emp_id, int pat_id, int cost, int visit_id, String reason, String date)
    {
       try{
            // Since the API perceives '/' as a new route, we need to manually insert them.
            date = new StringBuffer(date).insert(4, "/").toString();
            String newdate = new StringBuffer(date).insert(7, "/").toString();
            System.out.println(newdate);
            st.executeUpdate("INSERT INTO APPOINTMENT(EMP_ID , PAT_ID , VISIT_COST , VISIT_ID , VISIT_REASON , VISIT_TIME) VALUES (" + emp_id +" , " + pat_id + " , " + cost + " , " + visit_id + " , '" + reason + "' , " + " (TO_DATE('" + newdate + "', '" + "yyyy/mm/dd hh24:mi:ss" + "')))");
        }
        catch (SQLException e)
        {
           e.printStackTrace();
            return "Error";
        }
        return "Complete";
    }

    String removePat(int pid)
    {
        try{
            st.executeUpdate("DELETE FROM PATIENTS WHERE PAT_ID=" + pid);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error, unable to delete patient. Patient does not exist.";
        }
        return "Patient " + pid + " deleted.";
    }

    String removeAppt(int vid)
    {
        try{
            st.executeUpdate("DELETE FROM APPOINTMENT WHERE VISIT_ID=" + vid);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error, unable to delete appointment. Appointment does not exist.";
        }
        return "Patient " + vid + " deleted.";
    }
}
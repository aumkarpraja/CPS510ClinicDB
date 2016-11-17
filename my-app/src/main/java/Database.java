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
                    "jdbc:oracle:thin:@localhost:1521:orcl","aprajapa","11083404"); // @TODO: NEEDS TO BE CHANGED
            st = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected!");
        System.out.println("");
    }


    String getEmployees()
    {
        String result = "";
        result += "<h1>Employees</h1>";
        result += "<table border='1'><tr><th>e_Id</th><th>LastName</th><th>FirstName</th><th>Salary</th><th>EmployeeRole</th></tr>";

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

    String getPatients() {
        String result = "";
        result += "<h1>Patients</h1>";
        try{
            rs = st.executeQuery("SELECT * FROM PATIENTS");
            result += "<table border='1'><tr><th>P_Id</th><th>p_fname</th><th>p_lname</th><th>healthcard_num</th><th>p_address</th><th>p_Number</th><th>dob</th><th>bloodtype</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 8; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    String getAppointments()
    {
        String result = "";
        result += "<h1>Appointments</h1>";
        try{
            rs = st.executeQuery("SELECT * FROM APPOINTMENT");
            result += "<table border='1'><tr><th>P_Id</th><th>E_id</th><th>reason</th><th>AppointmentTime</th><th>Appointment_Id</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 5; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }

    String getPharm(){
        String result="";
        result += "<h1>PharmacistData</h1>";
        try{
            rs = st.executeQuery("SELECT * FROM PHARMACISTDATA");
            result += "<table border='1'><tr><th>P_Id</th><th>E_id</th><th>Prescription</th><th>Prescription_id</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 4; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";

        return result;
    }

    String getTestResults()
    {
        String result="";
        result += "<h1>TestResults</h1>";
        try{
            rs = st.executeQuery("SELECT * FROM TESTRESULTS");
            result += "<table border='1'><tr><th>P_Id</th><th>E_id</th><th>TestType</th><th>TestResult</th><th>Test_Id</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 5; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";
        return result;
    }


    String createAll(){
       try{
           st.executeUpdate("CREATE TABLE Employees" +
                   "(" +
                   "e_id NUMBER PRIMARY KEY," +
                   "lastname varchar(255) NOT NULL," +
                   "firstname varchar(255) NOT NULL," +
                   "employeesalary FLOAT(2) NOT NULL," +
                   "employeerole varchar(255) NOT NULL)");

           st.executeUpdate("CREATE TABLE Patients" +
                   "(" +
                   "p_id NUMBER NOT NULL PRIMARY KEY," +
                   "p_fname VARCHAR2(255) NOT NULL," +
                   "p_lname VARCHAR2(255) NOT NULL," +
                   "healthcard_num int NOT NULL," +
                   "p_address VARCHAR2(255) NOT NULL," +
                   "p_number VARCHAR2(12) NOT NULL," +
                   "dob varchar(11) NOT NULL," +
                   "bloodtype varchar(4) NOT NULL)");

           st.executeUpdate("CREATE TABLE Appointment" +
                   "(" +
                   "p_id NUMBER NOT NULL," +
                   "e_id NUMBER NOT NULL," +
                   "reason varchar(1024) NOT NULL," +
                   "appointmenttime TIMESTAMP NOT NULL," +
                   "appointment_id NUMBER NOT NULL PRIMARY KEY," +
                   "FOREIGN KEY (p_id) REFERENCES patients (p_id)," +
                   "FOREIGN KEY (e_id) REFERENCES employees (e_id))");

           st.executeUpdate("CREATE TABLE TestResults" +
                   "(" +
                   "p_id NUMBER NOT NULL," +
                   "e_id NUMBER NOT NULL," +
                   "testtype varchar(255)NOT NULL," +
                   "testresult varchar(1024) NOT NULL," +
                   "test_id NUMBER NOT NULL PRIMARY KEY," +
                   "FOREIGN KEY (p_id) REFERENCES patients (p_id)," +
                   "FOREIGN KEY (e_id) REFERENCES employees (e_id))");

           st.executeUpdate("CREATE TABLE PharmacistData" +
                   "(" +
                   "p_id NUMBER NOT NULL," +
                   "e_id NUMBER NOT NULL," +
                   "prescription varchar(1024) NOT NULL," +
                   "prescription_id NUMBER NOT NULL PRIMARY KEY," +
                   "FOREIGN KEY (p_id) REFERENCES patients (p_id)," +
                   "FOREIGN KEY (e_id) REFERENCES employees (e_id))");

            }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "<h1>Complete</h1>";
    }

    String dropAll(){
        try{
            rs = st.executeQuery("DROP TABLE APPOINTMENT");
            rs = st.executeQuery("DROP TABLE PHARMACISTDATA");
            rs = st.executeQuery("DROP TABLE TESTRESULTS");
            rs = st.executeQuery("DROP TABLE PATIENTS");
            rs = st.executeQuery("DROP TABLE EMPLOYEES");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return "<h1>Unable to drop all tables</h1>";
        }
        return "<h1>Complete</h1>";
    }

        String popAll(){
            try{
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (1, 'doctor', 100000, 'Michael', 'Scott')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (2, 'doctor', 100000, 'Amanda', 'Sherwyn')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (3, 'doctor', 100000, 'Sandy', 'Be')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (4, 'pharmacist', 100000, 'Andy', 'Smith')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (5, 'pharmacist', 100000, 'Jason', 'Bran')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (6, 'pharmacist', 100000, 'Samuel', 'Watson')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (7, 'nurse', 70000, 'Calvin', 'Moreno')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (8, 'nurse', 80000, 'Brandon', 'Graham')");
                st.executeUpdate("INSERT INTO EMPLOYEES (E_ID , EMPLOYEEROLE , EMPLOYEESALARY , FIRSTNAME , LASTNAME) VALUES (9, 'nurse', 75000, 'Julia', 'Shoes')");
                st.executeUpdate("INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (1, 'Mary', 'Doe', 123123123, '76 Claremont St', '4161111111', '06/17/90', 'O+')");
                st.executeUpdate("INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (2, 'Marie', 'Do', 123321321, '7 Claremont St', '4162221111', '01/17/92', 'O-')");
                st.executeUpdate("INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (3, 'Marnie', 'De', 123132123, '176 Claremont St', '4163331111', '02/17/94', 'O+')");
                st.executeUpdate("INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (4, 'Mar', 'Soe', 123123321, '376 Claremont St', '4164441111', '12/17/92', 'A+')");
                st.executeUpdate("INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (5, 'Varus', 'Uno', 123123213, '176 Claremont St', '4165551111', '10/17/92', 'B+')");
                st.executeUpdate("INSERT INTO PATIENTS (P_ID, P_FNAME, P_LNAME, HEALTHCARD_NUM, P_ADDRESS, P_NUMBER, DOB, BLOODTYPE) VALUES (6, 'Mort', 'Bone', 123123312, '476 Claremont St', '4166661111', '03/17/93', 'A-')");
                st.executeUpdate("INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (1,3, 'Wisdom tooth gum infection', (TO_DATE('2016/05/03 11:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130000)");
                st.executeUpdate("INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (3,3, 'Wisdom tooth minor gum infection', (TO_DATE('2016/05/03 12:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130001)");
                st.executeUpdate("INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (4,2, 'Strep throat', (TO_DATE('2016/05/03 11:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130002)");
                st.executeUpdate("INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (5,2, 'Nausea', (TO_DATE('2016/05/03 12:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130003)");
                st.executeUpdate("INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (6,1, 'Acne', (TO_DATE('2016/05/03 13:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130004)");
                st.executeUpdate("INSERT INTO APPOINTMENT (P_ID, E_ID, REASON, APPOINTMENTTIME, APPOINTMENT_ID) VALUES (2,1, 'Ear ringing', (TO_DATE('2016/05/03 14:10:00', 'yyyy/mm/dd hh24:mi:ss')), 100130005)");
                st.executeUpdate("INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (4, 1,'Amoxicillin 250mg tabs', 500500501)");
                st.executeUpdate("INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (4, 3,'Amoxicillin 50mg tabs', 500500502)");
                st.executeUpdate("INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (5, 4,'Clindymacin 250mg cap', 500500503)");
                st.executeUpdate("INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (5, 5,'Zofran 4mg tabs', 500500504)");
                st.executeUpdate("INSERT INTO PHARMACISTDATA (E_ID, P_ID, PRESCRIPTION, PRESCRIPTION_ID) VALUES (6, 6,'Bezaclin 250mg cap', 500500505)");
                st.executeUpdate("INSERT INTO TESTRESULTS(E_ID , P_ID , TESTRESULT , TESTTYPE, TEST_ID) VALUES (2,4, 'positive','strepthroat', 200200200)");
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return "<h1>Unable to populate all tables</h1>";
            }
            return "<h1>Complete</h1>";
        }

    /**
     * @TODO: This needs to be shortened
     * @return
     */
    String getAll()
    {
        String result ="";
        result += "<h1>Employees</h1>";
        result += "<table border='1'><tr><th>e_Id</th><th>LastName</th><th>FirstName</th><th>Salary</th><th>EmployeeRole</th></tr>";

        try{
            rs = st.executeQuery("SELECT * FROM EMPLOYEES");

            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 5; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>Patients</h1>";

        try{
            rs = st.executeQuery("SELECT * FROM PATIENTS");
            result += "<table border='1'><tr><th>P_Id</th><th>p_fname</th><th>p_lname</th><th>healthcard_num</th><th>p_address</th><th>p_Number</th><th>dob</th><th>bloodtype</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 8; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>Appointments</h1>";

        try{
            rs = st.executeQuery("SELECT * FROM APPOINTMENT");
            result += "<table border='1'><tr><th>P_Id</th><th>E_id</th><th>reason</th><th>AppointmentTime</th><th>Appointment_Id</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 5; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>TestResults</h1>";

        try{
            rs = st.executeQuery("SELECT * FROM TESTRESULTS");
            result += "<table border='1'><tr><th>P_Id</th><th>E_id</th><th>TestType</th><th>TestResult</th><th>Test_Id</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 5; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";

        result += "<h1>PharmacistData</h1>";
        try{
            rs = st.executeQuery("SELECT * FROM PHARMACISTDATA");
            result += "<table border='1'><tr><th>P_Id</th><th>E_id</th><th>Prescription</th><th>Prescription_id</th></tr>";
            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 4; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        result += "</table>";

        return result;
    }

    String advQuery1()
    {
        String result = "";
        result += "<table border='1'><tr><th>Appointment_id</th><th>e_id</th><th>p_id</th><th>Prescription</th></tr>";

        try{
            rs = st.executeQuery("SELECT appointment.appointment_id, appointment.e_id,appointment.p_id, pharmacistdata.prescription FROM appointment " +
                    "INNER JOIN pharmacistdata on appointment.p_id=pharmacistdata.p_id");

            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 4; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
            result+="</table>";;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery2()
    {
        String result = "";
        result += "<table border='1'><tr><th>Employee Count</th></tr>";

        try{
            rs = st.executeQuery("SELECT COUNT(EMPLOYEEROLE) FROM EMPLOYEES");

            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 2; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
            result+="</table>";;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery3()
    {
        String result = "";
        result += "<table border='1'><tr><th>Average Salary</th></tr>";

        try{
            rs = st.executeQuery("SELECT AVG(EMPLOYEESALARY) FROM EMPLOYEES");

            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 1; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
            result+="</table>";;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery4()
    {
        String result = "";
        result += "<table border='1'><tr><th>P_id</th><th>Prescription_id</th><th>Prescription</th></tr>";

        try{
            rs = st.executeQuery("SELECT DISTINCT appointment.p_id, pharmacistdata.prescription_id, " +
                    "pharmacistdata.prescription FROM appointment INNER JOIN pharmacistdata on appointment.p_id=pharmacistdata.p_id");

            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 3; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
            result+="</table>";;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    String advQuery5()
    {
        String result = "";
        result += "<table border='1'><tr><th>p_fname</th><th>Address Length</th></tr>";

        try{
            rs = st.executeQuery("SELECT p_fname,LENGTH(p_address) as LengthOfAddress FROM patients");

            while(rs.next())
            {
                result += "<tr>";
                for (int i = 1; i <= 2; i++)
                {
                    result += "<th>"+rs.getString(i) + "</th>";
                    result += "\t";
                }
                result+= "</tr>";
            }
            result+="</table>";;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }


}


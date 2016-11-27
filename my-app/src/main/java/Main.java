import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.*;

import java.math.BigInteger;


public class Main {
    public static int uid;


    public static void main(String[] args) {
        // Init the back-end database, SQL connections are made here
        final Database database = new Database();

        Spark.externalStaticFileLocation("/"); // temp fix, put site into root directory of system i.e C:/
        // Explanation: The StaticFileLocation command of this API is broken in this version.
        // Spark.staticFileLocation("/"); // needs to be fixed by demo

        // Prints the account statement
        get("/selectall", new Route() {
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return database.getAll();
            }
        });

        // Prints the account statement
        get("/createall", new Route() {
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return database.createAll();
            }
        });

        // Prints the account statement
        get("/dropall", new Route() {
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return database.dropAll();
            }
        });

        // Prints the account statement
        get("/popall", new Route() {
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return database.popAll();
            }
        });

        get("/select/:selectNum", new Route() {
            public Object handle(Request request, Response response) {
                int selectNum = Integer.parseInt(request.params(":selectNum"));

                if (selectNum == 0)
                    return database.getEmployees();
                else if (selectNum == 1)
                    return database.getPatients();
                else if (selectNum == 2)
                    return database.getAddress();
                else if (selectNum == 3)
                    return database.getPatientMedicalDetail();
                else if (selectNum == 4)
                    return database.getAppointment();
                else if (selectNum == 5)
                    return database.getVisitPrescription();
                else if (selectNum == 6)
                    return database.getMedications();
                else if (selectNum == 7)
                    return database.getVisitProcedure();
                else if (selectNum == 8)
                    return database.getMedicalProcedure();
                else
                    // this should never happen unless someone manually entered stuff
                    return "You are not authorized to be here. You clearly have used a command" +
                            "that is beyond the specified options or manually inputted data.";
            }
        });

        get("/advanced/:selectNum", new Route() {
            public Object handle(Request request, Response response) {
                int selectNum = Integer.parseInt(request.params(":selectNum"));

                if (selectNum == 0)
                    return database.advQuery1();
                else if (selectNum == 1)
                    return database.advQuery2();
                else if (selectNum == 2)
                    return database.advQuery3();
                else if (selectNum == 3)
                    return database.advQuery4();
                else if (selectNum == 4)
                    return database.advQuery5();
                else
                    // this should never happen unless someone manually entered stuff
                    return "You are not authorized to be here. You clearly have used a command" +
                            "that is beyond the specified options or manually inputted data.";
            }
        });

        //searches Patient table to find specific name given by user
        get("/searchpatientname/:firstname/:lastname", new Route(){
            public Object handle(Request request, Response response){
                String firstName = request.params("firstname"); //do not include colon in param
                String lastName = request.params("lastname");
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
                    return database.searchPatient(firstName + " " + lastName);
            }
        });

        //searches Employees table to find specific name
        get("/searchemployeename/:firstname/:lastname", new Route(){
            public Object handle(Request request, Response reponse){
                String firstName = request.params("firstname"); //do not include colon in param
                String lastName = request.params("lastname");
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
                return database.searchEmployee(firstName + " " + lastName);
            }
        });

        //Roles in Employees
        get("/searchemployeerole/:empname", new Route(){
            public Object handle(Request request, Response reponse){
                String employeeRole = request.params("empname");
                return database.searchEmployeeRole(employeeRole);
            }
        });

        get("/createpatient/:patid/:patfirstname/:patlastname/:patdob/:patgender/:addressid/:address/:city/:phone/:postal", new Route(){
            public Object handle(Request request, Response reponse){
                int pat_id = Integer.parseInt(request.params("patid"));
                String pat_firstname = request.params("patfirstname");
                String pat_lastname = request.params("patlastname");
                String pat_dob = request.params("patdob");
                String pat_gender = request.params("patgender");
                int address_id = Integer.parseInt(request.params("addressid"));
                String address = request.params("address");
                String city = request.params("city");
                long phone_num = Long.parseLong(request.params("phone"));
                String postal_code = request.params("postal");
                return database.createPat(pat_id, pat_firstname + " " + pat_lastname, pat_dob, pat_gender, address_id, address, city, phone_num, postal_code);
            }
        });

        get("/createappointment/:empid/:patid/:cost/:visitid/:reason/:date", new Route(){
            public Object handle(Request request, Response reponse){
                int emp_id = Integer.parseInt(request.params("empid"));
                int pat_id = Integer.parseInt(request.params("patid"));
                int cost = Integer.parseInt(request.params("cost"));
                int visit_id = Integer.parseInt(request.params("visitid"));
                String reason = request.params("reason");
                String date = request.params("date");
                return database.createAppt(emp_id, pat_id, cost, visit_id, reason, date);
            }
        });

        get("/removepatient/:pid", new Route(){
            public Object handle(Request request, Response reponse){
                String pid = request.params("pid");
                return database.removePat(Integer.parseInt(pid));
            }
        });

        get("/removeappointment/:vid", new Route(){
            public Object handle(Request request, Response reponse){
                String vid = request.params("vid");
                return database.removeAppt(Integer.parseInt(vid));
            }
        });


    }
}

	
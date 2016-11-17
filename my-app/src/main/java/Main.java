import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.*;


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
                    return database.getAppointments();
                else if (selectNum == 3)
                    return database.getPharm();
                else if (selectNum == 4)
                    return database.getTestResults();
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
    }
}

	
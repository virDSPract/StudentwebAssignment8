package studentwebassignment;





import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class studentwebassignment {

    public static void main(String[] args) {

        try {

            // Create server on port 8002
            HttpServer server = HttpServer.create(new InetSocketAddress(8006), 0);

            // Create endpoint
            server.createContext("/student", new StudentHandler());

            // Start server
            server.start();

            System.out.println("Server running at http://localhost:8006/student");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handler class
    static class StudentHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) {

            try {

                // Example URL:
                // http://localhost:8002/student?name=Viraj&roll=101&marks=85

                String query = exchange.getRequestURI().getQuery();

                String[] params = query.split("&");

                String name = params[0].split("=")[1];
                int roll = Integer.parseInt(params[1].split("=")[1]);
                int marks = Integer.parseInt(params[2].split("=")[1]);

                String result;

                // Check pass/fail
                if (marks >= 40) {
                    result = "PASS";
                } else {
                    result = "FAIL";
                }

                // Response
                String response =
                        "Student Name : " + name +
                        "\nRoll Number : " + roll +
                        "\nMarks : " + marks +
                        "\nResult : " + result;

                // Send response
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();

                os.write(response.getBytes());

                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}  // moduleinfo dlete karo
//remove the file folder module-info.java
//properties->libraries->modulepath->Add library any >11
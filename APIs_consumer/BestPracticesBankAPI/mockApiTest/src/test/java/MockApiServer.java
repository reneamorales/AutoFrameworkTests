import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class MockApiServer {

    public static void main(String[] args) {
        Gson gson = new Gson();

        post("/login", (request, response) -> handleLogin(request, response, gson));
    }

    private static String handleLogin(Request request, Response response, Gson gson) {
        // Parsear la solicitud JSON
        String body = request.body();
        System.out.println("Request Body: " + body);
        LoginRequest loginRequest = gson.fromJson(body, LoginRequest.class);
        System.out.println("Parsed Username: " + loginRequest.getUsername());
        System.out.println("Parsed Password: " + loginRequest.getPassword());

        // Simular la lógica de autenticación
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            response.status(201);
            return gson.toJson(new LoginResponse("token123", "Login successful"));
        } else {
            response.status(401);
            return gson.toJson(new ErrorResponse("Invalid credentials"));
        }
    }

    // Clases internas para representar las solicitudes y respuestas de login
    static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class LoginResponse {
        private String token;
        private String message;

        public LoginResponse(String token, String message) {
            this.token = token;
            this.message = message;
        }

        public String getToken() {
            return token;
        }

        public String getMessage() {
            return message;
        }
    }

    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
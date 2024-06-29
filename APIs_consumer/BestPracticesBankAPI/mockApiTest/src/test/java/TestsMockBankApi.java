
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class TestsMockBankApi {

    @Test
    public void limpiarDatosEndpoint() {

        // Realizar solicitud GET
        Response response = given().when().get("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");
        //Obteniendo la lista de usuarios como mapas
        List<Map<String, Object>> users = response.jsonPath().getList("");
        //Verificando que user no esté vacío
        if (!users.isEmpty()) {
            for (Map<String, Object> user : users) {
                // Realizando solicitud DELETE para cada usuario usando su ID
                given().when().delete("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users/" + user.get("id"));
            }
        }

        //Verificando que el endpoint se encuentre totalmente limpio

        response = given().when().get("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");

        Assert.assertTrue(response.jsonPath().getList("").isEmpty());
        System.out.println("Endpoint luego de limpiarlo: " + response.asString());
    }

    @Test
    public void pruebaCrearUsuarios() {
        // Instaciamos la clase Faker para implementar datos
        Faker faker = new Faker();
        // Creamos una lista para crear los usuarios
        List<Map<String, Object>> users = new ArrayList<>();
        // Creamos un Set para evitar correos repetidos
        Set<String> correos = new HashSet<>();

        //Creación de 10 usuarios a través de bucle for
        for (int i = 1; i <= 10; i++) {
            // Variable para crear email
            String email;

            // Condicional de creación mientras el email no este contenido dentro del Set
            do {
                email = faker.internet().emailAddress();

            } while (correos.contains(email));
            // Agregado de correo nuevo al Set
            correos.add(email);
            // Creación de objetos usuario y cuenta
            Map<String, Object> user = new HashMap<>();
            Map<String, Object> account = new HashMap<>();

            // Agregado de valores correspondientes al objeto cuenta
            account.put("accountNumber", faker.number().randomNumber());
            account.put("money", faker.number().randomDouble(2, 0, 800000));
            account.put("id", i);
            // Agregado de valores correspondientes al objeto usuario
            user.put("name", faker.name().fullName());
            user.put("password", faker.internet().password());
            user.put("email", email);
            user.put("id", i);
            user.put("account", account);
            //Agregado del nuevo usuario al objeto usuarios.
            users.add(user);
        }

        // Método POST para el envío del Objeto usuarios de uno en uno
        for (Map<String, Object> user : users) {
            Response response = given().contentType("application/json")
                    .body(user)
                    .when()
                    .post("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");
            Assert.assertEquals(response.getStatusCode(), 201, "Error al crear el usuario: " + user.get("email"));
            //Impresion por consola de datos envíados
            System.out.println(response.asString());
        }

    }


    @Test
    public void verificandoDuplicaciónDeCorreos() {

        Response response = given().when().get("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");

        // Creación de lista para correos del endpoint
        List<String> correos = new ArrayList<>();
        //Objención de valores de email
        correos = response.jsonPath().getList("email");

        // Variable Set para agregar correos
        Set<String> emailSet = new HashSet<>();

        // Iteración del listado
        for (String correo : correos) {

            //Condicional si la clave no contiene el elemento nulo
            if (correo != null) {
                // Condicional de avisio si el correo no se puede agregar dentro del Set.
                if (!emailSet.add(correo)) {
                    System.out.println("ALERTA: correo duplicado" + correo);
                }
            }
        }
        //Aserción de mismo tamaño de las variables
        Assert.assertEquals(emailSet.size(), correos.size());

    }

    @Test
    public void actualizandoNumeroDeCuenta() {
        RestAssured.baseURI = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

        //Consulta al endpoint usuario de Id = 5 "users/5"
        Response response = given()
                .when()
                .get("/users/5")
                .then()
                .statusCode(200)
                .extract().response();
        // Obtención del objeto del usuario
        Map<String, Object> user = response.jsonPath().getMap("");
        // Obtención del objeto de su cuenta
        Map<String, Object> account = (Map<String, Object>) user.get("account");
        // Agregado de nuevo número de cuenta.
        account.put("accountNumber", 987654321);

        //Envío del nuevo valor por medio del método PATCH.
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .patch("/users/5")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        // Obtención del usuario nuevamente
        response = given()
                .when()
                .get("/users/5")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Usuario modificado " + response.asString());
    }

    @Test
    public void verificaciónDeDeposito() {

        RestAssured.baseURI = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

        Response response = given()
                .get("/users/6")
                .then()
                .statusCode(200)
                .extract().response();

        // Obtención del objeto del usuario
        Map<String, Object> user = response.jsonPath().getMap("");
        // Obtención del objeto de su cuenta
        Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

        // Obtención del valor de dinero actual
        double dineroActual = response.jsonPath().getDouble("account.money");
        double nuevoValor = dineroActual + 320.20;

        // Agregado de nuevo valor
        accountMoney.put("money", nuevoValor);

        //Envío a través de método PATCH
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .patch("users/6")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        //Impresión de Objeto envíado
        System.out.println(response.asString());

    }

    @Test
    public void verificacionDeRetiroDinero() {
        RestAssured.baseURI = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

        Response response = given()
                .get("/users/9")
                .then()
                .statusCode(200)
                .extract().response();
        // Obtención del objeto del usuario y posteriormente su cuenta
        Map<String, Object> user = response.jsonPath().getMap("");
        Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

        //Obtenición de valor actual de dinero.
        double dineroActual = response.jsonPath().getDouble("account.money");
        double extraccion = dineroActual - 110.20; // Descuento de valor

        //Agregado del nuevo valor al objeto money
        accountMoney.put("money", extraccion);

        //Envío de valores a través de método PATCH
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .patch("users/9")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
        // Impresión de Objeto enviado
        System.out.println(response.asString());
    }

}
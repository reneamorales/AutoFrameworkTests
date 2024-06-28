import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
        //Verificando que user no este vacío
        if (!users.isEmpty()) {
            for (Map<String, Object> user : users) {
                // Realizar solicitud DELETE para cada usuario usando su ID
                given().when().delete("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users/" + user.get("id"));
            }
        }

        //Verificando que el enpoint se encuentre totalmente limpio

        response = given().when().get("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");

        Assert.assertTrue(response.jsonPath().getList("").isEmpty());

        System.out.println(response.asString());
    }

    @Test
    public void pruebaCrearUsuarios() {
        Faker faker = new Faker();

        List<Map<String, Object>> users = new ArrayList<>();

        Set<String> correos = new HashSet<>();

        for (int i = 1; i <= 10; i++) {

            String email;

            do {
                email = faker.internet().emailAddress();

            } while (correos.contains(email));

            correos.add(email);

            Map<String, Object> user = new HashMap<>();
            Map<String, Object> account = new HashMap<>();

            account.put("accountNumber", faker.number().randomNumber());
            account.put("money", faker.number().randomDouble(2, 0, 800000));
            account.put("id", i);

            user.put("name", faker.name().fullName());
            user.put("password", faker.internet().password());
            user.put("email", email);
            user.put("id", i);
            user.put("account", account);

            users.add(user);
        }


        for (Map<String, Object> user : users) {
            Response response = given().contentType("application/json")
                    .body(user)
                    .when()
                    .post("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");
            Assert.assertEquals(response.getStatusCode(), 201, "Error al crear el usuario: " + user.get("email"));

            System.out.println(response.asString());
        }


    }

    @Test
    public void verificandoDuplicaciónDeCorreos() {

        Response response = given().when().get("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");

        List<String> correos = new ArrayList<>();
        correos = response.jsonPath().getList("email");

        Set<String> emailSet = new HashSet<>();

        for (String correo : correos) {

            if (correo != null) {
                if (!emailSet.add(correo)) {
                    System.out.println("ALERTA: correo duplicado" + correo);
                }
            }
        }
        Assert.assertEquals(emailSet.size(), correos.size());

    }

    @Test
    public void actualizandoNumeroDeCuenta() {
        RestAssured.baseURI = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

        Response response = given()
                .when()
                .get("/users/5")
                .then()
                .statusCode(200)
                .extract().response();

        Map<String, Object> user = response.jsonPath().getMap("");

        Map<String, Object> account = (Map<String, Object>) user.get("account");
        account.put("accountNumber", 987654321);


        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .patch("/users/5")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();


        response = given()
                .when()
                .get("/users/5")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Numero de cuenta despues de modificar " + response.asString());
    }

    @Test
    public void verificaciónDeDeposito() {

        RestAssured.baseURI = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

        Response response = given()
                .get("/users/6")
                .then()
                .statusCode(200)
                .extract().response();


        Map<String, Object> user = response.jsonPath().getMap("");
        Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

        double dineroActual = response.jsonPath().getDouble("account.money");
        double nuevoValor = dineroActual + 320.20;


        accountMoney.put("money", nuevoValor);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .patch("users/6")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        System.out.println(response.asString());

    }

    @Test
    public void verificacionDeRetiroDinero(){
        RestAssured.baseURI = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

        Response response = given()
                .get("/users/9")
                .then()
                .statusCode(200)
                .extract().response();

        Map<String, Object> user = response.jsonPath().getMap("");
        Map<String, Object> accountMoney = (Map<String, Object>) user.get("account");

        double dineroActual = response.jsonPath().getDouble("account.money");
        double extraccion = dineroActual - 110.20;

        accountMoney.put("money", extraccion);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .patch("users/9")
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        System.out.println(response.asString());
    }


}
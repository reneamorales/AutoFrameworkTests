import io.restassured.response.Response;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class TestsMockBankAPI {

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
    public void pruebaCrearUsuarios(){
        Faker faker = new Faker();

        List<Map<String, Object>> users = new ArrayList<>();

       Set<String> correos = new HashSet<>();

        for (int i = 0; i < 10; i++) {

            String email;

            do{
                email = faker.internet().emailAddress();

            }while(correos.contains(email));

            correos.add(email);

            Map<String,Object> user = new HashMap<>();

            user.put("name", faker.name().fullName());
            user.put("password", faker.internet().password());
            user.put("email", email);
            user.put("id", i);

            users.add(user);

        }


      for (Map<String, Object> user: users){
          Response response = given().contentType("application/json")
                  .body(user)
                  .when()
                  .post("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");
          Assert.assertEquals(response.getStatusCode(), 201, "Error al crear el usuario: " + user.get("email"));
      }


    }
@Test
    public void verificandoDuplicaciónDeCorreos(){

        Response response = given().when().get("https://665145ff20f4f4c4427756bc.mockapi.io/api/v1/users");

        List<String> correos = new ArrayList<>();
        correos = response.jsonPath().getList("email");

        Set<String> emailSet = new HashSet<>();

        for (String correo : correos){

            if (correo != null){
                if(!emailSet.add(correo)){
                    System.out.println("ALERTA: correo duplicado" + correo);
                }
            }
        }
        Assert.assertEquals(emailSet.size(), correos.size());

    }
}
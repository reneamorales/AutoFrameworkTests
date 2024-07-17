import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class apiBase {

    protected static final String BASE_URL = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

    protected Response getRequest(String endpoint){
        return given().when().get(BASE_URL + endpoint);
    }

    protected Response postRequest(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint); // No concatenar BASE_URL aquí si ya está configurada globalmente
    }

    protected Response patchRequest(String endpoint, Object body){
        return given().contentType(ContentType.JSON).body(body).when().patch(BASE_URL + endpoint);
    }

    protected Response deleteRequest(String endpoint){
        return given().when().delete(BASE_URL + endpoint);
    }


}

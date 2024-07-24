import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Clase base para realizar solicitudes HTTP a la API.
 * Esta clase proporciona métodos para realizar solicitudes GET, POST, PATCH y DELETE.
 *
 * @author René Morales
 */
public class ApiBase {

    /**
     * URL base de la API.
     */
    protected static final String BASE_URL = "https://665145ff20f4f4c4427756bc.mockapi.io/api/v1";

    /**
     * Realiza una solicitud GET al endpoint especificado.
     *
     * @param endpoint El endpoint al que se realizará la solicitud.
     * @return La respuesta de la solicitud GET.
     */
    protected Response getRequest(String endpoint){

        return
                given().get(BASE_URL + endpoint)
                .then()
                .statusCode(200)
                .extract().response();

    }

    /**
     * Realiza una solicitud POST al endpoint especificado con el cuerpo proporcionado.
     *
     * @param endpoint El endpoint al que se realiza la solicitud.
     * @param body El cuerpo de la solicitod en formato JSON.
     * @return La respuesta de la solicitud POST.
     */
    protected Response postRequest(String endpoint, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(BASE_URL + endpoint); // No concatenar BASE_URL aquí si ya está configurada globalmente
    }

    /**
     * Realiza una solicitud PATCH al endpoint especificado con el cuerpo proporcionado.
     *
     * @param endpoint El endpoint al que se realizará la solicitud.
     * @param body El cuerpo de la solicitud en formato JSON.
     * @return La respuesta de la solicitud PATCH.
     */
    protected Response patchRequest(String endpoint, Object body){
        return given().contentType(ContentType.JSON).body(body).when().patch(BASE_URL + endpoint);
    }

    /**
     *  Realiza una solicitud DELETE al endpoint especificado.
     * @param endpoint El endpoint al que se realiza la solicitud.
     * @return La respuesta de la solicitud DELETE.
     */
    protected Response deleteRequest(String endpoint){
        return given().when().delete(BASE_URL + endpoint);
    }


}

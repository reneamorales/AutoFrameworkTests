import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.Test;pub

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class starWarsTest {

    @Test
    public void endpointResponseSuccessful() {

        Response response;
        response = given().get("https://swapi.dev/api/people/2/");
        
        Assert.assertEquals(response.getStatusCode(), 200);

        String skinColor = response.jsonPath().getString("skin_color");
        System.out.println("Color de piel: " + skinColor);
        assertEquals("gold", skinColor);

        int filmsCount = response.jsonPath().getList("films").size();
        assertEquals(6, filmsCount);

    }
    
    @Test
    public void endpointResponseSuccessfulDateFormat() {

        Response response;
        response = given().get("https://swapi.dev/api/people/2/");
        JsonPath jsonPathEvaluator = response.jsonPath();

        String filmUrl = jsonPathEvaluator.getList("films").get(1).toString();

        Response filmResponse = given().get(filmUrl);
        JsonPath filmJsonPathEvaluator = filmResponse.jsonPath();

        String releaseDate = filmJsonPathEvaluator.get("release_date");

        assertThat(releaseDate, matchesPattern("\\d{4}-\\d{2}-\\d{2}"));
        //characters, planets, starships, vehicles, and species
        assertThat(filmJsonPathEvaluator.getMap("$"),allOf(hasKey("characters"), hasKey("planets"), hasKey("starships"), hasKey("vehicles"), hasKey("species")));

        assertThat(filmJsonPathEvaluator.getList("characters").size(), greaterThan(1));
        assertThat(filmJsonPathEvaluator.getList("planets").size(), greaterThan(1));
        assertThat(filmJsonPathEvaluator.getList("planets").size(), greaterThan(1));
        assertThat(filmJsonPathEvaluator.getList("vehicles").size(), greaterThan(1));
        assertThat(filmJsonPathEvaluator.getList("species").size(), greaterThan(1));

    }


    public void TestPlanetsDetails(){
        // Realiza una solicitud GET al endpoint de las películas
        Response filmResponse = RestAssured.get("https://swapi.dev/api/films/");

        //Extraemos la cantidad total de las peliculas
        int totalFilms = filmResponse.jsonPath().getInt("count");

        //Realizamos la solicitud GET a la última pelicula.
        Response lastFilmsResponse = RestAssured.get("https://swapi.dev/api/films/" + totalFilms + "/");

        // Extrae el endpoint del primer planeta presente en la respuesta del último film
        String firstPlanetUrl = lastFilmsResponse.jsonPath().getList("planets").get(0).toString();

        // Realiza una solicitud GET al endpoint del primer planeta
        Response planetResponse = RestAssured.get(firstPlanetUrl);


        // Comprueba que la gravedad y los terrenos coinciden con los valores esperados
        String expectedGravity = "1 standard";
        List<String> expectedTerrains = Arrays.asList("grasslands", "mountains");

        //Comparamos los valores antes declarados con los que se obtienen de la solicitud
        Assert.assertEquals(expectedGravity, planetResponse.jsonPath().getString("gravity"));
        Assert.assertEquals(expectedTerrains, planetResponse.jsonPath().getList("terrain"));

        //Ralizamos una nueva solicitud GET al endpoint del primer planeta que se obtuvo anteriormente, almacenandolo en una variable.
        Response expectedUrl= RestAssured.get(firstPlanetUrl);
        //Verificamos que la respuesta e exactamente a la anterior.
        Assert.assertEquals(planetResponse,expectedUrl );

    }

    public void verifiedFilmSeven(){
        // Solicita el /films/7/ y comprueba que la respuesta tiene un código 404.
        Response filmSeven= RestAssured.get("https://swapi.dev/api/films/7/");
        Assert.assertEquals(filmSeven.getStatusCode(), 404);
    }
}

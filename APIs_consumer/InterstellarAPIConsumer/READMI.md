## Testeo de Consumo de API Swapi

#### Este repositorio contiene un proyecto para probar el consumo de la Api de Swapi utilizando TestNG y Restassured como dependencias.
Se utilizan las versiones 5.3.2 de Restassured y 7.8.0 de TestNG.

---

### Especificaciones de las pruebas

Haciendo uso de JAVA, RestAssured, cualquier test runner y una arquitectura framework adecuada para organizar y reutilizar las llamadas a la API (¡No olvides POJOS!).

Utilizaremos la API de Star Wars (swapi) para este ejercicio, la URL base es https://swapi.dev/ y necesitamos Implementar pruebas (una prueba diferente para cada solicitud) realizando las siguientes acciones:

1. Probar el endpoint people/2/ y comprobar la respuesta de éxito, que el color de la piel sea dorado, y la cantidad de películas en las que aparece (deberían ser 6).

2. Solicitar el endpoint de la segunda película en la que aparece people/2/ (utilizando la respuesta de people/2/). Comprueba que la fecha de estreno esté en el formato de fecha correcto, y que la respuesta incluya personajes, planetas, naves estelares, vehículos y especies, y que cada elemento incluya más de 1 elemento.

3. Solicitar el punto final del primer planeta presente en la respuesta de la última película (utilizando la respuesta anterior). Comprueba que la gravedad y los terrenos coinciden exactamente con los valores devueltos por la solicitud (Utiliza fixtures para almacenar y comparar los datos de terrenos y gravedad).

4. En la misma respuesta del planeta, coge el elemento url de la respuesta, y solicítalo. Valida que la respuesta es exactamente igual a la anterior.

5. Solicita el /films/7/ y comprueba que la respuesta tiene un código 404.


---

### Marco de Prueba

Se realizan las siguientes Pruebas

_**Primer conjunto de Pruebas:**_

**Prueba 1:** Endpoint y respuesta correcta

- Hacemos una petición GET a la API de Star Wars y almacenamos la respuesta en nuestra variable.
- Verificamos que el código de estado de la respuesta sea 200.
- Extraemos el color de piel del personaje de la respuesta y lo almacenamos en una variable.
- Imprimimos el color de piel del personaje.
- Verificamos que el color de piel del personaje sea "gold".
- Extraemos la cantidad de películas en las que aparece el personaje y lo almacenamos en una variable.
- Verificamos que el personaje aparezca en 6 películas.


**Prueba 2:** Formato de fecha de lanzamiento y elementos de la película

- Hacemos una petición GET a la API de Star Wars para obtener información sobre un personaje específico y almacenamos la respuesta.
- Extraemos la URL de una de las películas en las que aparece el personaje.
- Hacemos una petición GET a la URL de la película y almacenamos la respuesta.
- Extraemos la fecha de lanzamiento de la película y verificamos que sigue el formato correcto (YYYY-MM-DD).
- Verificamos que la respuesta incluye claves para “characters”, “planets”, “starships”, “vehicles” y “species”.
- Para cada uno de estos elementos, verificamos que la lista correspondiente tenga más de un elemento.

**Prueba 3:** 

- Realiza una solicitud GET al endpoint de las películas.
- Extraemos la cantidad total de las peliculas.
- Realizamos la solicitud GET a la última pelicula.
- Extrae el endpoint del primer planeta presente en la respuesta del último film.
- Realiza una solicitud GET al endpoint del primer planeta.

- Comprueba que la gravedad y los terrenos coinciden con los valores esperados.
- Comparamos los valores antes declarado,con los que se obtienen de la solicitud

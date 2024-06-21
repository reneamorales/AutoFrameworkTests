## Testeo de Consumo de API Swapi

#### Este repositorio contiene un proyecto para probar el consumo de la Api de Swapi utilizando Cypress
Se utilizan las versiones ^9.7.0 de Cypress.

**Cypress Avanzado: Pruebas API - Ejercicio**

**_Práctica Requerida**

**API a probar:** [https://swapi.dev/](https://swapi.dev/ "https://swapi.dev/")

Para esta práctica, vamos a probar una API real utilizando la versión de cypress que más te guste, y una arquitectura de framework adecuada para organizar y reutilizar las llamadas a la API e implementar escenarios de prueba.

Utilizaremos la API de Star Wars (swapi) para este ejercicio, la URL base es [https://swapi.dev/](https://swapi.dev/ "https://swapi.dev/") y necesitamos Implementar pruebas (una prueba diferente para cada solicitud) realizando las siguientes acciones:

1. Probar el endpoint **people/2/** y comprobar la respuesta de éxito, que el color de la piel sea dorado y la cantidad de películas en las que aparece (debería ser 6).
    
 2. Solicitar el endpoint de la segunda película en la que **people/2/** estaba presente (utilizando la respuesta de **people/2/**). Comprueba que la fecha de estreno esté en el formato de fecha correcto, y que la respuesta incluya personajes, planetas, naves estelares, vehículos y especies, incluyendo cada elemento más de 1 elemento.
    
3. Solicitar el punto final del primer planeta presente en la respuesta de la última película (utilizando la respuesta anterior). Comprueba que la gravedad y los terrenos coinciden exactamente con los mismos valores devueltos por la petición (Utiliza fixtures para almacenar y comparar los datos de terrenos y gravedad).
    
4. En la misma respuesta del planeta, coge el elemento url de la respuesta, y solicítalo. Valida que la respuesta sea exactamente igual a la anterior.

5. Solicite el **/films/7/** y compruebe que la respuesta tiene un código 404.

---

### Marco de Prueba

Se realizan las siguientes Pruebas

_**Conjunto de Pruebas:**_

**Prueba 1:** Endpoint Respuesta Éxito completo

- Hacemos una petición GET a la API de Star Wars al enpoint **people/2/**
Verificamos que el color de piel sea "gold".
- Verificamos la cantidad de peliculas en las que aparece people/2.

- Solicitando el endpoint de la segunda película, utilizando la respuesta anterior
- Verificando el éxito de la respuesta

- Verificamos la fecha de estreno sigue el formato correcto (YYYY-MM-DD).
- Verificamos que la respuesta incluye las claves “characters”, “planets”, “starships”, “vehicles” y “species”.
- Para cada uno de estos elementos, verificamos que la lista correspondiente tenga más de un elemento.

- Solicitamos el endpoint de la ultima pelicula, utilizando la respuesta anterior.
- Verificamos el éxito de la respuesta

- Solicitamos el endpoint del primer planeta, utilizando la respuesta anterior.
- Verificando el éxito de la respuesta.
- Verificamos el nombre, el terreno y la gravedad del planeta.
- Hacemos uso fixture como objeto  terrainAndGravity con las propiedades 'terrain' y 'gravity' hacemos las comparaciones.

- Resolicitamos el endpoint para volver a comparar el cuerpo de su respuesta.
- Comparamos el cuerpo de las respuesta sean iguales.

**Prueba 1:** Verificamos la pelicula 7
- Hacemos una petición GET a la API de Star Wars al endpoint **/films/7**. 
- Verificamos que el código de estado sea 404.



## ## Testeo de Consumo de API bancaria de MockApi

#### Este repositorio contiene un proyecto para probar el consumo de la Api de un mockApi bancario utilizando TestNG, Restassured y DataFaker como dependencias.
Se utilizan las versiones 5.3.2 de Restassured, 7.8.0 de TestNG y 2.2.2 de Datafaker.


---

### Especificaciones de las pruebas


**Ejercicio de Mejores Prácticas**

Para esta práctica, vamos a implementar las mejores prácticas para probar una API bancaria real.

Comienza creando una cuenta en el siguiente enlace [https://www.mockapi.io/projects](https://www.mockapi.io/projects "https://www.mockapi.io/projects") y configura un endpoint para transacciones bancarias (Añadir usuario, iniciar sesión, depositar dinero, recuperar dinero).

Una vez creado el endpoint, define las mejores prácticas a implementar y luego, comencemos a probar los siguientes escenarios en tu framework de automatización:

- **Prueba 1:** Verifica que el endpoint esté vacío (Si tiene algún dato usa la petición DELETE para limpiar y dejarlo vacío, usa hooks o anotaciones).

- **Prueba 2:** Inicializa el POJO con 10 datos de usuario aleatorios (usa los requisitos mínimos).  Además, realiza una verificación de código para evitar cuentas de correo duplicadas. A continuación, realiza la petición POST para crear el usuario.

- **Prueba 3:** Realiza la petición GET para obtener los usuarios, afirmando que no hay cuentas de correo duplicadas.

- **Prueba 4:** Añade una prueba para actualizar un AccountNumber existente.

- **Prueba 5:** Añadir una prueba para verificar el depósito correcto de dinero.

- **Prueba 6:** Añadir una prueba para verificar la correcta retirada de dinero.


Para implementar las mejores prácticas ten en cuenta los siguientes consejos:

- Estructure el proyecto haciendo que las peticiones sean reutilizables, evite repetir código o endpoints, utilice TestNG, cree un archivo Readme.md con las especificaciones y los pasos para ejecutar el ejercicio y añada un gitignore.


- Para cada solicitud, asegúrese de incluir al menos una aserción para el Código de Estado (implemente POJOs para manejar los datos de respuesta, no sólo el cuerpo) y asegúrese de utilizar JavaDoc.


---

### Marco de Prueba

Se realizan las siguientes Pruebas

_**Primer conjunto de Pruebas:**_

**Prueba 1:** Endpoint y respuesta correcta


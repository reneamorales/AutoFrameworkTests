## Testeo de página web con Selenium

#### Este repositorio contiene un proyecto para probar la funcionalidad de resultado de Youtube utilizando  Selenium y TestNG como dependencias.

---

### Especificaciones de Prueba

_**Estás trabajando en la automatización de la funcionalidad de búsqueda de la aplicación web YouTube. El proceso de búsqueda implica introducir una consulta, hacer clic en el botón de búsqueda y validar los resultados. Necesitas implementar aserciones TestNG para verificar la precisión de los resultados de la búsqueda.**_


1. Configuración del proyecto: Crea un nuevo proyecto Selenium - Java - TestNG y configúralo para lanzar y probar YouTube en el navegador Chrome.
2. Búsqueda de TestNG: Implementa un método de prueba TestNG que busque “TestNG” en YouTube y valide cada uno de los resultados de la búsqueda. Puedes usar aserciones TestNG para comparar los resultados reales de la búsqueda con los resultados esperados.
3. Búsqueda de Selenium: Implementa un método de prueba similar al anterior, pero cambia el valor de búsqueda de “TestNG” a “Selenium”. Adapta las aserciones de los resultados de la prueba según sea necesario.
4. Comparación de resultados de búsqueda: Implementa un nuevo método de prueba que valide que los resultados de la búsqueda de “Selenium” son diferentes de los de la búsqueda de “TestNG”. Utiliza varias aserciones en esta prueba.
5. Prueba de inicio de sesión: Añade una prueba que fallará, esperando que YouTube muestre el formulario de inicio de sesión justo después de buscar “Log In”. Asegúrate de crear aserciones para todos los elementos necesarios en el formulario de inicio de sesión.

---

### Scripts de Prueba
Primer conjunto de pruebas: Prueba de búsquedas de TestNG

Prueba 1: Búsqueda de “TestNG” en YouTube
Esta prueba realiza una búsqueda de “TestNG” en YouTube y valida que los resultados de la búsqueda contengan “TestNG” o “TESTNG”.

Los pasos de la prueba son los siguientes:

- Navegación al sitio web de YouTube
- Identificación del cuadro de búsqueda
- Envío de texto “TestNG” para búsqueda
- Identificación y clic en el botón de búsqueda
- Obtención y validación de los resultados de la búsqueda


Segundo conjunto de pruebas: Prueba de búsquedas de Selenium

**Prueba 1:** Búsqueda de “Selenium” en YouTube
Esta prueba realiza una búsqueda de “Selenium” en YouTube y valida que los resultados de la búsqueda contengan “Selenium”.

Los pasos de la prueba son los siguientes:

- Navegación al sitio web de YouTube
- Identificación del cuadro de búsqueda
- Envío de texto “Selenium” para búsqueda
- Identificación y clic en el botón de búsqueda
- Obtención y validación de los resultados de la búsqueda

**Prueba 2:** Comparación de resultados de búsqueda para “TestNG” y “Selenium”
Esta prueba realiza dos búsquedas distintas en YouTube, una para “TestNG” y otra para “Selenium”, y luego compara los resultados de ambas búsquedas para asegurar que son diferentes.

Los pasos de la prueba son los siguientes:

- Navegación a sitio web
- Identificación del cuadro de búsqueda
- Envío de texto para búsqueda
- Identificación y clic en el botón de búsqueda
- Obtención de los resultados de la primera búsqueda

- Navegación a sitio web
- Identificación del cuadro de búsqueda
- Envío de otro texto para búsqueda
- Identificación y clic en el botón de búsqueda
- Obtención de los resultados de la primera búsqueda

- Comparación de contenido de elementos a elementos de las listas mediante un fori
- Verificación de la cantidad de elementos de las listas

**Prueba 3:** Esta prueba de fallo, se llama prueba de inicio de sesión después de hacer la búsqueda y su objetivo es verificar que el formulario de inicio de sesión aparezca después de realizar una búsqueda de “Log In” en YouTube. 

Descripción paso a paso:

- Navegación a YouTube
- Búsqueda de “Log In”
- Verificación del formulario de inicio de sesión









## Testeo de página web con Selenium


#### Este repositorio contiene un proyecto para probar la funcionalidad del ecommerce de prueba SauceDemo utilizando  Selenium y TestNG como dependencias.

---

_**El marco completo de automatización se ha construido utilizando el Modelo de Objetos de Página (POM), con una clase dedicada para cada página, conjuntos de pruebas y clases base. Esto facilita la comprensión y el mantenimiento del framework. Se utilizan las versiones 4.15.0 de Selenium y 7.8.0 de TestNG.**_

Se realizan las siguientes pruebas:

_**Primer conjunto de pruebas:**_ Compra de un producto.

**Prueba 1:**

- Inicio de sesión con credenciales.
- Obtención del título de la nueva página para su posterior validación utilizando SoftAssert.
- Agregado de un producto al carrito de compras de manera aleatoria.

**Prueba 2:**

- Obtención del título de la página del carrito después del inicio de sesión y continuación del proceso.
- Validación del título de la nueva página de carga de credenciales.
- Configuración de las credenciales del usuario para continuar con el flujo de compra.

**Prueba 3:**

- Obtención de la última instancia del proceso.
- Clic en el botón para finalizar el proceso de compra.
- Aserción para validar el texto del resultado de la compra.

---


_**Segundo conjunto de pruebas:**_ Eliminación de productos del carrito

**Prueba 1:**

- Obtención de la instancia de la página de Inicio de Sesión.
- Inicio de sesión utilizando credenciales.
- Verificación del título de la página utilizando SoftAssert.
- Selección de tres productos de forma aleatoria.
- Click en el botón para ir al carrito de compras.

- Instanciación del carrito de compras.
- Impresión del título de la página del carrito.
- Eliminación precisa de todos los productos utilizando la interfaz Iterator del framework de Collections.
- Verificación de que la cantidad de productos en el carrito es 0.

---

**Tercer conjunto de prueba:** Cierre de sesión

**Prueba 1:**

- Obtención de la instancia de la página de Inicio de Sesión.
- Inicio de sesión utilizando credenciales.
- Obtención de la instancia de menú navbar.
- Clickeo en menú.
- Selección de icono de deslogueo.
- Aserción de deslogueo a través de la verificación de título la página principal del ecommerce.


**Importante:** El primer conjunto de prueba son dependiente del éxito de los anteriores en el caso del segundo y tercero.
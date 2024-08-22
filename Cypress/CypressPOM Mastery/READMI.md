## Prueba con POM Avanzado

#### Este repositorio contiene un proyecto con la finalidad de implementar pruebas  utilizando Cypress
Se utiliza la versión: ^9.7.0 de Cypress.

**Cypress Avanzado: Pruebas API - Ejercicio**

**Práctica Requerida**

En este ejercicio, se debe implementar el patrón de diseño **Page Object Model (POM)**  para automatizar el proceso de inicio de sesión en un sitio web. Se interactúa con campos de entrada, botones y etiquetas para verificar que el inicio de sesión se ha realizado correctamente. El formulario con el que se interactúa es el siguiente: https://practicetestautomation.com/practice-test-login

### Instrucciones

Para este ejercicio debemos tener en cuenta las siguientes instrucciones:

- Crear los objetos page para las páginas **login** y **home** del sitio web.
    
- En función del objeto página, obtener los diferentes elementos necesarios para la ejecución de los casos de prueba.
    
- Definir las diferentes acciones a ejecutar haciendo uso de los elementos.
    
- Crear el archivo de especificaciones de inicio de sesión e implementar pruebas para el inicio de sesión correcto y la introducción incorrecta de credenciales.
    
- Crear objetos de página para las páginas **Blog** y **Cursos** a partir de la página web dada.
    
- Crear nuevos archivos de especificaciones para probar la navegación respectiva a las páginas Blog y Cursos, y su correcta visualización (Comprobación de la visibilidad de sus elementos importantes).



---

### Marco de Prueba


**Conjunto de Pruebas:**

1. **Login Exitoso:**
- Cargar la página de inicio.
- Iniciar sesión con credenciales correctas.
- Verificar que el mensaje de éxito se muestra y que se puede cerrar sesión.

2. **Login con Usuario Incorrecto:**
- Cargar la página de inicio.
- Iniciar sesión con un usuario incorrecto.
- Verificar que se muestra el mensaje de error "Your username is invalid!".

3. **Login con Contraseña Incorrecta:**
- Cargar la página de inicio.
- Iniciar sesión con una contraseña incorrecta.
- Verificar que se muestra el mensaje de error "Your password is invalid!"

4. **Navegación a la Página de Cursos:**
- Navegar a la página de cursos desde la barra de navegación.
- Verificar que el título de la página se muestra correctamente.

5. **Navegación a la Página de Blog:**
- Navegar a la página de blog desde la barra de navegación.
- Verificar que los elementos importantes se muestran correctamente.
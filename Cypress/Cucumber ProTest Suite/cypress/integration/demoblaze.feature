Feature: Navegación automática del carrusel 
Como usuario del sitio web 
Quiero que el carrusel de elementos destacados se desplace automanticamente
Para que pueda ver los diferentes elementos sin intervención manual

@carousel @automatic @regression
Scenario: Verificar que el carrusel se desplace automanticamente entre elementos
Given que estoy en la pagina de inicio
When espero 6 segundos
Then el siguiente elemento del carrusel deberia estar activo

Scenario: Verificar que el carrusel se desplace cuand clickeo el boton para cambiar al siguiente
Given que estoy en la pagina de inicio
When hago clic en el boton de siguiente
Then el siguiente elemento del carrusel deberia estar activo


Feature: Navegación a las diferentes páginas desde la barra de navegación
  Como usuario del sitio,
  Quiero hacer uso de los links disponibles en la barra de navegación
  Para visualizar la información correspondiente en cada una de las páginas

  Scenario Outline: Verificar la funcionalidad de los enlaces que abren modales
    Given que estoy en la pagina de inicio
    When clickeo sobre el enlace de <linksSelectors>
    Then debería ver el modal correspondiente <modals>

    Examples:
      | linksSelectors | modals |
      | linkAboutAs | modal  |
      | linkAboutUs | modal  |

import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

Given ('que estoy en la pagina de inicio', ()=>{
    cy.visit('/');
})

When('espero 6 segundos', () =>{
    cy.wait(4000);
})
Then('el siguiente elemento del carrusel deberia estar activo', ()=>{
    cy.get(".carousel-item.active")  // Selecciona el elemento que tiene la clase 'active'
    .invoke("index")
    .then((initialIndex) => {
      cy.wait(2000);  // Espera otros 3 segundos para permitir que el carrusel se desplace
      cy.get(".carousel-item.active")  // Selecciona el nuevo elemento que se ha vuelto activo
        .invoke("index")
        .should("not.eq", initialIndex);  // Verifica que el índice haya cambiado
    });


    When('hago clic en el boton de siguiente', () =>{
        cy.get('.carousel-control-next-icon').click();
    });
/*Debería implementar la navegacion de a las páginas desde los carruseles pero, no tienen redirecctión*/


});
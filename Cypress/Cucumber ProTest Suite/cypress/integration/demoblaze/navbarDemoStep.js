import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";
import HomePage from "../../support/pages/HomePage.js";

const homePage = new HomePage();

Given ('que estoy en la pagina de inicio', ()=>{
    cy.visit('/');
})

When('clickeo sobre el enlace de {string}', (linksSelectors) => {
    console.log(`Link Selector recibido: ${linksSelectors}`);
    homePage.clickNavbarLink(linksSelectors);
})

Then('debería ver el modal correspondiente {string}', (modals) => {
    homePage.verifyModalContains(modals);
});
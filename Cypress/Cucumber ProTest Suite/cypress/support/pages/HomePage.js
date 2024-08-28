var PageLocators = {
    linkContacts: "nav.navbar.navbar-toggleable-md.bg-inverse:nth-child(5) div.navbar-collapse ul.navbar-nav.ml-auto li.nav-item:nth-child(2) > a.nav-link",
    linkAboutAs: "nav.navbar.navbar-toggleable-md.bg-inverse:nth-child(5) div.navbar-collapse ul.navbar-nav.ml-auto li.nav-item:nth-child(3) > a.nav-link",
    modal: ".modal-content"
}

class HomePage{
    constructor(){
        this.linkContacts = PageLocators.linkContacts;
        this.linkAboutUs = PageLocators.linkAboutAs;
        this.modal= PageLocators.modal;
    }
   clickNavbarLink(link){
    cy.get(link).click();
   }

   verifyModalContains(modal){
    cy.get(modal).should('be.visible');
   }



}

export default HomePage;
var pageLocators = {
    usernameInput: "#username",
    passwordInput: "#password",
    buttonSubmit: "#submit",
    menssajeLogueo: ".post-title",
    logOutButton: ".wp-block-button__link",
    labelError: "#error"
};

class LoginPage {
    usernameInput(student) { 
        return cy.get(pageLocators.usernameInput).type(student + '{enter}');
    }
    
    passwordInput(password) { 
        return cy.get(pageLocators.passwordInput).type(password + '{enter}');
    }
    
    buttonSubmit() { 
        return cy.get(pageLocators.buttonSubmit).click();
    }

    messaje(){
        return cy.get(pageLocators.menssajeLogueo, {timeout:15000}).should('contain', 'Logged In Successfully');
    }

    logOut(){
        return cy.get(pageLocators.logOutButton).click();
    }

    labelError(){
        return cy.get(pageLocators.labelError).should('be.visible');
    }
    userErrorMessage(){
        return cy.get(pageLocators.labelError).should('contain', 'Your username is invalid!');
    }
    passwordErrorMessage(){
        return cy.get(pageLocators.labelError).should('contain', 'Your password is invalid!');
    }
}

export default LoginPage;

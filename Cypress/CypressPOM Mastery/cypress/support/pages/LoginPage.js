var pageLocators = {
    usernameInput: "#username",
    passwordInput: "#password",
    buttonSubmit: "#submit",
    messajeLogIn: ".post-title",
    logOutButton: ".wp-block-button__link",
    labelError: "#error"
};

class LoginPage{

    login(user, password){
        this.usernameInput(user);
        this.passwordInput(password);
        this.loginButton();
    }
    
    usernameInput(user){
        return cy.get(pageLocators.usernameInput).type(user + '{enter}');
    }
    passwordInput(password){
        return cy.get(pageLocators.passwordInput).type(password + '{enter}');
    }
    loginButton(){
        return cy.get(pageLocators.buttonSubmit).click();
    }
    message(){
        return cy.get(pageLocators.messajeLogIn).should('contain', 'Logged In Successfully')
    }
    logOut(){
        return cy.get(pageLocators.logOutButton).click();
    }
    labelError(){
        return cy.get(pageLocators.labelError).should('be.visible');
    }
    errorMessage(expectedMessage){
        return cy.get(pageLocators.labelError).should('contain', expectedMessage)
    }
    
}

export default LoginPage;

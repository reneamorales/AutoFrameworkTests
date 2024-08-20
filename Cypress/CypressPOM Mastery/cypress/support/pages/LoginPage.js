var pageLocators = {
    usernameInput: "#username",
    passwordInput: "#password",
    buttonSubmit: "#submit",
    messajeLogIn: ".post-title",
    logOutButton: ".wp-block-button__link",
    labelError: "#error"
};

class LoginPage{
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
    userErrorMessage(){
        return cy.get(pageLocators.labelError).should('contain', 'Your username is invalid!')
    }
    passwordErrorMessage(){
        return cy.get(pageLocators.labelError).should('contain', 'Your password is invalid!')
    }
}

export default LoginPage;

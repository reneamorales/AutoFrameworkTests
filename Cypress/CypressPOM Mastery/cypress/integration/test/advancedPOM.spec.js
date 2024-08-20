import LoginPage from "../../support/pages/LoginPage.js";

const loginPage = new LoginPage();

context('login-with-credentials', () => {
    beforeEach(() => {
        cy.visit('/');
        cy.fixture('login-credentials').as('credentials');
    });

    it('Successful login', () => {
        cy.get('@credentials').then((credentials) => {
            
                loginPage.usernameInput(credentials.usuarios[0].username);
                loginPage.passwordInput(credentials.usuarios[0].password);
                loginPage.buttonSubmit();
    
                loginPage.messaje();
                loginPage.logOut();
        
            
        });
    });

    it('Login with incorrect user', () => {
        cy.get('@credentials').then((credentials) => {

            loginPage.usernameInput(credentials.usuarios[1].username);
            loginPage.passwordInput(credentials.usuarios[1].password);
            loginPage.buttonSubmit();

            loginPage.labelError();
            loginPage.userErrorMessage();
        });
    });

    it('Login with incorrect password', () => {
        cy.get('@credentials').then((credentials) => {

            loginPage.usernameInput(credentials.usuarios[2].username);
            loginPage.passwordInput(credentials.usuarios[2].password);
            loginPage.buttonSubmit();

            loginPage.labelError();
            loginPage.passwordErrorMessage();
        });
    });

    
});

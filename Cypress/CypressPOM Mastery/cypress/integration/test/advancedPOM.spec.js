
import LoginPage from "../../support/pages/LoginPage.js";
import NavbarPage from "../../support/pages/NavbarPage.js";
import CoursesPage from "../../support/pages/CoursesPage.js";
import BlogPage from "../../support/pages/BlogPage.js";

const loginPage = new LoginPage();
const navbarPage = new NavbarPage();
const coursesPage = new CoursesPage();
const blogPage = new BlogPage();


context('login-with-credentials', () => {
    beforeEach(() => {
        cy.visit('/');
        cy.fixture('login-credentials').as('credentials');
    });

    it('Successful login', () => {
        cy.get('@credentials').then((credentials) => {
            loginPage.usernameInput(credentials.users[0].username);
            loginPage.passwordInput(credentials.users[0].password);
            loginPage.loginButton();

            loginPage.message();
            loginPage.logOut()
        });
    });

    it('Login with incorrect user', () => {
        cy.get('@credentials').then((credentials) => {
            loginPage.usernameInput(credentials.users[1].username);
            loginPage.passwordInput(credentials.users[1].password);
            loginPage.loginButton();

            loginPage.labelError();
            loginPage.userErrorMessage();
            
        });
    });
    it('Login with incorrect password', () => {
        cy.get('@credentials').then((credentials) => {
            loginPage.usernameInput(credentials.users[2].username);
            loginPage.passwordInput(credentials.users[2].password);
            loginPage.loginButton();

            loginPage.labelError();
            loginPage.passwordErrorMessage();
            
        });
    });

    it('Navigate to Courses Page', ()=>{
      navbarPage.clickCourses();
      coursesPage.getTitle();
    });

    it('Navigate to Blog Page', ()=>{
        navbarPage.clickBlog();
        blogPage.h3verify();
    })
});
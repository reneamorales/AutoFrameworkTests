
import LoginPage from "../../support/pages/LoginPage.js";
import NavbarPage from "../../support/pages/NavbarPage.js";
import CoursesPage from "../../support/pages/CoursesPage.js";
import BlogPage from "../../support/pages/BlogPage.js";

const loginPage = new LoginPage();
const navbarPage = new NavbarPage();
const coursesPage = new CoursesPage();
const blogPage = new BlogPage();
const loginWithCredentials= (index)=>{
    cy.get('@credentials').then((credentials) =>{
      loginPage.login(credentials.users[index].username, credentials.users[index].password);
    });
};


context('login-with-credentials', () => {
    beforeEach(() => {
        cy.visit('/');
        cy.fixture('login-credentials').as('credentials');
    });

    it('Successful login', () => {
            loginWithCredentials(0);

            loginPage.message();
            loginPage.logOut()
    });

    it('Login with incorrect user', () => {
            loginWithCredentials(1);

            loginPage.labelError();
            loginPage.errorMessage('Your username is invalid!');
            

    });
    it('Login with incorrect password', () => {
            loginWithCredentials(2);

            loginPage.labelError();
            loginPage.errorMessage('Your password is invalid!');
            
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
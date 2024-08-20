var pageLocators = {
    linkCourses: "#menu-primary-items > #menu-item-21",
    linkBlog: "#menu-primary-items > #menu-item-19"
}

class NavbarPage{
    clickCourses(){
        return cy.get(pageLocators.linkCourses).click();
    }
    clickBlog(){
        return cy.get(pageLocators.linkBlog).click();
    }
}

export default NavbarPage;
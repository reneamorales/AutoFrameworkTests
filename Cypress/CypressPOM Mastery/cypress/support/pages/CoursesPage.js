var pageLocators = {
    'titlePage': ".post-title"
}

class CoursesPage{
    title(){return cy.get(pageLocators.title).should(contain('Courses'))}
}
export default CoursesPage;
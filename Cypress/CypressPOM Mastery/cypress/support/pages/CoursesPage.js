var pagelocators = {
    title: ".post-title"
}

class CoursesPage{
    getTitle() {
        return cy.get(pagelocators.title).should('contain', 'Courses');
    }
}
export default CoursesPage;
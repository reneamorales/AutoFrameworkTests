var pageLocators = {
    subtitle: ".wp-block-heading"
}

class BlogPage {
    h3verify() {
        return cy.get(pageLocators.subtitle).should('contain', 'Introduction')
    }
}
export default BlogPage;
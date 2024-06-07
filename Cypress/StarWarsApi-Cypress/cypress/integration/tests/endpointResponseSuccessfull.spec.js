context('tests', () => {

    it('endpointResponseSuccessfull', () => {
        cy.request({
            method: 'GET',
            url: 'https://swapi.dev/api/people/2/',

        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body).to.have.property("skin_color").to.eq("gold");
            expect(response.body.films.length).to.eq(6);
        })
    });

    it('endpointResponseSuccessfulDateFormat', () => {
        cy.request({
            method: 'GET',
            url: 'https://swapi.dev/api/films/2/',

        }).then((response) =>{
            expect(response.status).to.eq(200);
            expect(response.body.release_date).to.match(/^\d{4}-\d{2}-\d{2}$/);
            expect(response.body).to.have.property("characters").to;
            expect(response.body).to.have.property("planets");
            expect(response.body).to.have.property("starships");
            expect(response.body).to.have.property("vehicles");
            expect(response.body).to.have.property("species");

            expect(response.body.characters).to.have.length.greaterThan(1);
            expect(response.body.planets).to.have.length.greaterThan(1);
            expect(response.body.starships).to.have.length.greaterThan(1);
            expect(response.body.vehicles).to.have.length.greaterThan(1);
            expect(response.body.species).to.have.length.greaterThan(1);

        })

        /*personajes, planetas, naves estelares, vehículos y especies */
    })
});
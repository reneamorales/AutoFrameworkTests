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

        }).then((response) => {
            expect(response.status).to.eq(200);
            expect(response.body.release_date).to.match(/^\d{4}-\d{2}-\d{2}$/);
            expect(response.body).to.have.property("characters");
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

    it('TestPlanetsDetails', () => {
        cy.request({
            method: 'GET',
            url: 'https://swapi.dev/api/films/',
        }).then((response) => {
            expect(response.body.count).to.eq(6);
            const lastFilmId = response.body.count;
            cy.request({
                method: 'GET',
                url: 'https://swapi.dev/api/films/' + lastFilmId,
            }).then((lastFilmResponse) => {
                expect(lastFilmResponse.status).to.eq(200);
                expect(lastFilmResponse.body.title).to.eq("Revenge of the Sith");
                const firstPlanet = lastFilmResponse.body.planets[0];
                cy.request({
                    method: 'GET',
                    url: firstPlanet,
                }).then((firstPlanetEndpoint) => {
                    expect(firstPlanetEndpoint.status).to.eq(200);
                    expect(firstPlanetEndpoint.body.name).to.eq('Tatooine');
                    expect(firstPlanetEndpoint.body.gravity).to.eq('1 standard');
                    expect(firstPlanetEndpoint.body.terrain).to.eq('desert');
                    cy.request({
                        method: 'GET',
                        url: 'https://swapi.dev/api/planets/1/',
                    }).then((repeatFirstPlanetEndpoint) => {
                        expect(repeatFirstPlanetEndpoint.body).to.deep.equal(firstPlanetEndpoint.body);
                    });
                });
            });
        });
    });

    it('verifiedFilmSeven', () => {
        cy.request({
            method: 'GET',
            url: 'https://swapi.dev/api/films/7/',
            failOnStatusCode: false
        }).then((sevenFilm) => {
            expect(sevenFilm.status).to.eq(404);
        })
    });
});


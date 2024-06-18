context('tests', () => {

    it('endpointResponseSuccessfull', () => {
        //Prueba el punto final people/2/
        cy.request({
            method: 'GET',
            url: 'https://swapi.dev/api/people/2/',

        }).then((response) => {
            //Comprobando la respuesta de éxito
            expect(response.status).to.eq(200);
            //Verificando que el color de piel sea gold.
            expect(response.body).to.have.property("skin_color").to.eq("gold");
            //Verificando la cantidad de peliculas en las que aparece.
            expect(response.body.films.length).to.eq(6);
            const responsePeople2 = response.body.films[1];

           
            //Solicitando el endpoint de la segunda película, utilizando la respuesta anterior
            cy.request({
                method: 'GET',
                url: responsePeople2,
            }).then((responsePeople) => {
                //Verificando el éxito de la respuesta
                expect(responsePeople.status).to.eq(200);
                expect(responsePeople.body.title).to.eq('The Empire Strikes Back');
                //Verificando que el fomato de la fecha sea el esperado
                expect(responsePeople.body.release_date).to.match(/^\d{4}-\d{2}-\d{2}$/);
                //Verificando que contenga las props. personajes, planetas, naves estelares, vehículos y especies 
                expect(responsePeople.body).to.have.property("characters");
                expect(responsePeople.body).to.have.property("planets");
                expect(responsePeople.body).to.have.property("starships");
                expect(responsePeople.body).to.have.property("vehicles");
                expect(responsePeople.body).to.have.property("species");
                //Verificando que cada elemento contenga más de 1
                expect(responsePeople.body.characters).to.have.length.greaterThan(1);
                expect(responsePeople.body.planets).to.have.length.greaterThan(1);
                expect(responsePeople.body.starships).to.have.length.greaterThan(1);
                expect(responsePeople.body.vehicles).to.have.length.greaterThan(1);
                expect(responsePeople.body.species).to.have.length.greaterThan(1);
                //Solicitando el endpoint de la ultima pelicula, utilizando la respuesta anterior
                cy.request({
                    method: 'GET',
                    url: response.body.films[response.body.films.length - 1],
                }).then((responseLast) => {
                    //Verificando el éxito de la respuesta
                    expect(responseLast.status).to.eq(200);
                    expect(responseLast.body.title).to.eq("Revenge of the Sith");
                    const firstPlanet = responseLast.body.planets[0];
                    //Solicitando el endpoint del primer planeta, utilizando la respuesta anterior
                    cy.request({
                        method: 'GET',
                        url: firstPlanet,
                    }).then((firstPlanetEndpoint) => {
                        //Verificando el éxito de la respuesta
                        expect(firstPlanetEndpoint.status).to.eq(200);
                        //Verificando el nombre, el terreno y la gravedad del planeta.
                        expect(firstPlanetEndpoint.body.name).to.eq('Tatooine');
                        expect(firstPlanetEndpoint.body.terrain).to.eq(fixtures.);
                        expect(firstPlanetEndpoint.body.gravity).to.eq('1 standard');
                        //Resolicitando el endpoint para volver a comparar el cuerpo de su respuesta.
                        cy.request({
                            method: 'GET',
                            url: firstPlanetEndpoint.body.url,
                        }).then((repeatFirstPlanetEndpoint) => {
                            //Comparando el cuerpo de las respuesta
                            expect(firstPlanetEndpoint.body).to.deep.equal(repeatFirstPlanetEndpoint.body);
                        });
                    });
                });
            })

        })
    });

    //Soliciando el endpoint del fiml 7 para verificar que contiene codigo 404
    it('verifiedFilmSeven', () => {
        cy.request({
            method: 'GET',
            url: 'https://swapi.dev/api/films/7/',
            // Esto previene que Cypress falle la prueba por códigos de estado no 2xx o 3xx
            failOnStatusCode: false
        }).then((sevenFilm) => {
            //Verificando el código
            expect(sevenFilm.status).to.eq(404);
        })
    });
});


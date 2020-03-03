package controller;

import client.StarWarsClient;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.StarWarsPlanet;

/**
 *
 * @author sleam
 */
@Named
@RequestScoped
public class StarWarsPlanetController {

    String input;
    List<StarWarsPlanet> planets = new ArrayList<>();

    @Inject
    StarWarsClient client;

    public StarWarsPlanetController() {

    }

    public void findByID() {
        StarWarsPlanet planet = client.findStarWarsPlanetById(input);
        if (planet != null) {
            planets.add(planet);
        }
    }

    public void findByName() {
        planets.addAll(client.getAllSearched(input));
    }

    public void showAll() {
        planets.addAll(client.getAll());
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<StarWarsPlanet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<StarWarsPlanet> planets) {
        this.planets = planets;
    }

}

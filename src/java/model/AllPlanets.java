package model;

import java.util.List;

public class AllPlanets {

    List<StarWarsPlanet> results;
    private String next;

    public AllPlanets() {
    }

    public List<StarWarsPlanet> getResults() {
        return results;
    }

    public void setResults(List<StarWarsPlanet> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}

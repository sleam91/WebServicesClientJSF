package model;

public class StarWarsPlanet {

    String name;
    String diameter;
    String climate;
    String terrain;
    String population;

    public StarWarsPlanet() {
    }

    public StarWarsPlanet(String name, String diameter, String climate, String terrain, String population) {
        this.name = name;
        this.diameter = diameter;
        this.climate = climate;
        this.terrain = terrain;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "StarWarsPlanet{" + "name=" + name + ", diameter=" + diameter + ", climate=" + climate + ", terrain=" + terrain + ", population=" + population + '}';
    }

}

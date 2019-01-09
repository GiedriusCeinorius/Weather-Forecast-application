package com.gce.weatherforecastapp.helpers;

import com.gce.weatherforecastapp.model.Coordinates;

public class CityBasicInfo {

    private String name;
    private String country;
    private Long population;
    private Coordinates coord;

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}

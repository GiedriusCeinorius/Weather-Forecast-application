package com.gce.weatherforecastapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City implements Serializable {

    @Id
    private Long id;
    private String name;
    private String country;
    private Long population;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Coordinates coord;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "city")
    private List<WeatherForecast> weatherForecasts;

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<WeatherForecast> getWeatherForecasts() {
        return weatherForecasts;
    }

    public void setWeatherForecasts(List<WeatherForecast> weatherForecast) {
        this.weatherForecasts = weatherForecast;
    }

    public void addForecast(WeatherForecast forecast) {
        if (forecast != null) {
            if (weatherForecasts == null) {
                weatherForecasts = new ArrayList<>();
            }
            forecast.setCity(this);
            weatherForecasts.add(forecast);
        }
    }


}


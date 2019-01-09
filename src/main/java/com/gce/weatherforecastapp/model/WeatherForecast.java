package com.gce.weatherforecastapp.model;

import com.gce.weatherforecastapp.helpers.WeatherConditions;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WeatherForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dateTime;
    private int tempMin;
    private int tempMax;
    private WeatherConditions weatherConditions;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public WeatherConditions getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(WeatherConditions weatherConditions) {
        this.weatherConditions = weatherConditions;
    }


    public void setCity(City city) {
        this.city = city;
    }
}







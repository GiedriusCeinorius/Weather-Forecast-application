package com.gce.weatherforecastapp.model;

import com.gce.weatherforecastapp.helpers.WeatherConditions;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class WeatherForecastFull extends WeatherForecast {

    private int tempAvg;
    private WeatherConditions weatherConditions;
    private String pressure;
    private String humidity;

    public int getTempAvg() {
        return tempAvg;
    }

    public void setTempAvg(int tempAvg) {
        this.tempAvg = tempAvg;
    }

    public WeatherConditions getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(WeatherConditions weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

}




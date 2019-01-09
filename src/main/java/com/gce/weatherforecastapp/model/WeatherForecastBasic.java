package com.gce.weatherforecastapp.model;

import com.gce.weatherforecastapp.helpers.WeatherConditions;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class WeatherForecastBasic extends WeatherForecast {
}


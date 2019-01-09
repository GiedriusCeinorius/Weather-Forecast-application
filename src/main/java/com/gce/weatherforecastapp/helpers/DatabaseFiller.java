package com.gce.weatherforecastapp.helpers;

import com.gce.weatherforecastapp.DAO.CitiesRepository;
import com.gce.weatherforecastapp.model.City;
import com.gce.weatherforecastapp.model.WeatherForecastFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class DatabaseFiller {

    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    private GeneratedValues generatedValues;

    @Autowired
    private DateTimeManager dateManager;


    public void fillDatabase(City city) {

        WeatherForecastFull weatherFull = new WeatherForecastFull();
        weatherFull.setTempMin(generatedValues.generateTempValue(-5, 2));
        weatherFull.setTempMax(generatedValues.generateTempValue(4, 8));
        weatherFull.setTempAvg(Math.round(weatherFull.getTempMax() + weatherFull.getTempMin() / 2));

        weatherFull.setHumidity(generatedValues.generateHumidityValue(45.6, 100.0) + "%");
        weatherFull.setPressure(generatedValues.generatePreasureValue(1003, 1013) + "hPa");

        weatherFull.setWeatherConditions(generatedValues.generateWeatherConditions());
        weatherFull.setDateTime(dateManager.formatCurrentDateTime(LocalDateTime.now()));

        city.addForecast(weatherFull);

        LocalDateTime midnightToday = LocalDateTime.now().toLocalDate().atStartOfDay();
        Duration dur = Duration.ofHours(3);
        LocalDateTime midnightAfterFiveDays = midnightToday.plusDays(5);

        while (midnightToday.isBefore(midnightAfterFiveDays)) {

            WeatherForecastFull weatherFullForThreeHours = new WeatherForecastFull();
            weatherFullForThreeHours.setTempMin(generatedValues.generateTempValue(-5, 2));
            weatherFullForThreeHours.setTempMax(generatedValues.generateTempValue(4, 8));
            weatherFullForThreeHours.setTempAvg(Math.round(weatherFull.getTempMax() + weatherFull.getTempMin() / 2));

            weatherFullForThreeHours.setHumidity(generatedValues.generateHumidityValue(45.6, 100) + "%");
            weatherFullForThreeHours.setPressure(generatedValues.generatePreasureValue(1003, 1013) + "hPa");

            weatherFullForThreeHours.setWeatherConditions(generatedValues.generateWeatherConditions());
            weatherFullForThreeHours.setDateTime(dateManager.formatCurrentDateTime(midnightToday));

            midnightToday = midnightToday.plus(dur);

            city.addForecast(weatherFullForThreeHours);
        }
        citiesRepository.save(city);
    }
}

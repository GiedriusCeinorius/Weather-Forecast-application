package com.gce.weatherforecastapp.helpers;


import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class GeneratedValues {

    public int generateTempValue(int min, int max) {

        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public WeatherConditions generateWeatherConditions() {
        List<WeatherConditions> contitions = Arrays.asList(WeatherConditions.values());
        Collections.shuffle(contitions);
        return contitions.stream().findFirst().get();
    }

    public int generatePreasureValue(int min, int max) {

        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public String generateHumidityValue(double min, double max) {

        double range = (max - min) + 1;
        double value = (Math.random() * range) + min;
        DecimalFormat df = new DecimalFormat("#.##");
        String fValue = df.format(value);
        return fValue;
    }
}

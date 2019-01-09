package com.gce.weatherforecastapp.service;

import com.gce.weatherforecastapp.DAO.CitiesRepository;
import com.gce.weatherforecastapp.model.City;
import com.gce.weatherforecastapp.model.Coordinates;
import com.gce.weatherforecastapp.model.WeatherForecast;
import com.gce.weatherforecastapp.model.WeatherForecastBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicWeatherForecastFilter {

    @Autowired
    private CitiesRepository repo;

    public List<City> citiesBasicWeatherInfo() {

        List<City> list = new ArrayList<>();

        for (City c : repo.findAll()) {
            list.add(collectBasicInfo(c));
        }
        return list;
    }

    public City cityExtendedWeatherInfo(String cityName) {

        City c = repo.findByName(cityName);
        City city = new City();
        city.setId(c.getId());
        city.setName(c.getName());
        city.setCountry(c.getCountry());
        city.setPopulation(c.getPopulation());
        city.setCoord(c.getCoord());
        WeatherForecast weather = c.getWeatherForecasts().get(0);
        city.addForecast(weather);

        return city;
    }

    public List<City> citiesBasicWeatherInfoByCountry(String country) {

        List<City> list = new ArrayList<>();

        for (City c : repo.findAllByCountry(country)) {
            if (c.getCountry().equals(country)) {
                list.add(collectBasicInfo(c));
            }
        }

        return list;
    }

    public List<City> citiesBasicWeatherInfoByPopulation(Long from, Long to) {

        List<City> list = new ArrayList<>();
        for (City c : repo.findByPopulationBetween(from, to)) {
            list.add(collectBasicInfo(c));
        }

        return list;
    }

    public City collectBasicInfo(City c) {

        City city = new City();
        city.setId(c.getId());
        city.setName(c.getName());
        city.setCountry(c.getCountry());
        city.setPopulation(c.getPopulation());
        city.setCoord(c.getCoord());
        WeatherForecast weather = c.getWeatherForecasts().get(0);
        WeatherForecastBasic basicForecast = new WeatherForecastBasic();
        basicForecast.setDateTime(weather.getDateTime());
        basicForecast.setTempMin(weather.getTempMin());
        basicForecast.setTempMax(weather.getTempMax());
        basicForecast.setWeatherConditions(weather.getWeatherConditions());
        city.addForecast(basicForecast);

        return city;
    }


    public City weatherInfoByCoordinates(Coordinates coord, Boolean trueFalse) {
        City c = null;
        for (City city : repo.findAll()) {
            if (city.getCoord().equals(coord)) {
                if (trueFalse == true) {
                    c = city;
                    break;
                } else {
                    c = collectBasicInfo(city);
                    break;
                }
            } else {
                c = dummyCity();
            }
        }
        return c;
    }

    public City dummyCity() {

        City city = new City();
        city.setId(0L);
        city.setName("Not Found");
        city.setCountry("Not Found");
        city.setPopulation(0L);
        Coordinates c = new Coordinates();
        c.setLat(0.0);
        c.setLon(0.0);
        city.setCoord(c);
        city.addForecast(null);
        city.addForecast(null);

        return city;
    }
}


package com.gce.weatherforecastapp.controller;

import com.gce.weatherforecastapp.DAO.CitiesRepository;
import com.gce.weatherforecastapp.helpers.CityBasicInfo;
import com.gce.weatherforecastapp.model.City;
import com.gce.weatherforecastapp.model.Coordinates;
import com.gce.weatherforecastapp.service.BasicWeatherForecastFilter;
import com.gce.weatherforecastapp.service.CityCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    @Autowired
    private CitiesRepository citiesRepository;
    @Autowired
    private BasicWeatherForecastFilter basicFilter;
    @Autowired
    private CityCollector cityCollector;

    @GetMapping("/get/basicWeatherInfoAllCities")
    public List<City> getAllCitiesBasic() {
        return basicFilter.citiesBasicWeatherInfo();
    }

    @GetMapping("/get/basicWeatherInfoByCountry/{country}")
    public List<City> getCitiesBasicInfoByCountry(@PathVariable String country) {
        return basicFilter.citiesBasicWeatherInfoByCountry(country);
    }

    @GetMapping("/get/basicWeatherInfoByPopulation/from/{from}/to/{to}")
    public List<City> getCitiesBasicInfoByPopulation(@PathVariable Long from, @PathVariable Long to) {
        return basicFilter.citiesBasicWeatherInfoByPopulation(from, to);
    }

    @RequestMapping(value = {"/get/extendedWeatherInfoByCity/{cityName}", "/get/extendedWeatherInfoByCity/{cityName}/{fiveDaysForecast}"}, method = RequestMethod.GET)
    public City getCityExtendedInfo(@PathVariable String cityName, @PathVariable Optional<String> fiveDaysForecast) {
        if (fiveDaysForecast.isPresent() && fiveDaysForecast.get().equals("fiveDaysForecast")) {
            return citiesRepository.findByName(cityName);
        } else {
            if (citiesRepository.findByName(cityName) != null) {
                return basicFilter.cityExtendedWeatherInfo(cityName);
            } else {
                return basicFilter.dummyCity();
            }
        }
    }

    @PostMapping("/add/cityForForecast")
    public String addCityForTheForecast(@RequestBody CityBasicInfo cityBasicInfo) {
        return cityCollector.addCity(cityBasicInfo);
    }

    @RequestMapping(value = {"/get/WeatherInfoByCoordinates", "/get/weatherInfoByCoordinates/{fiveDaysForecast}"}, method = RequestMethod.GET)
    public City getWeatherInfoByCoordinates(@RequestBody Coordinates coordinates, @PathVariable Optional<String> fiveDaysForecast) {
        City city = null;
        if (fiveDaysForecast.isPresent() && fiveDaysForecast.get().equals("fiveDaysForecast")) {
            city = basicFilter.weatherInfoByCoordinates(coordinates, true);
        } else {
            city = basicFilter.weatherInfoByCoordinates(coordinates, false);
        }
        return city;
    }

    @DeleteMapping("/remove/cityWithName/{cityName}")
    public String deleteCityFromAvailableCities(@PathVariable String cityName) {
        String message = null;
        if (citiesRepository.findByName(cityName) == null) {
            message = "There is no city " + cityName + "!";
        } else {
            citiesRepository.deleteById(citiesRepository.findByName(cityName).getId());
            message = "City " + cityName + " successfully deleted!";
        }
        return message;
    }

    @PutMapping("/update/City")
    public String updateCityInfo(@RequestBody CityBasicInfo city) {
        return cityCollector.updateCity(city);
    }
}




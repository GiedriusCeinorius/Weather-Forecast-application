//package com.gce.weatherforecastapp.service;
//
//import com.gce.weatherforecastapp.DAO.CitiesRepository;
//import com.gce.weatherforecastapp.helpers.CityBasicInfo;
//import com.gce.weatherforecastapp.helpers.DatabaseFiller;
//import com.gce.weatherforecastapp.helpers.JSONReaderWriter;
//import com.gce.weatherforecastapp.model.City;
//import com.gce.weatherforecastapp.model.Coordinates;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//
//@Service
//public class CityCollector {
//
//    @Autowired
//    private DatabaseFiller filler;
//    @Autowired
//    private CitiesRepository repo;
//    @Autowired
//    private JSONReaderWriter JSONwriter;
//
//    public String addCity(CityBasicInfo cityBasicInfo) {
//
//        String cityName = cityBasicInfo.getName();
//
//        if (repo.findByName(cityName) == null) {
//
//            String country = cityBasicInfo.getCountry();
//            Long population = cityBasicInfo.getPopulation();
//            Coordinates coord = cityBasicInfo.getCoord();
//            Long cityID = null;
//
//
//            for (Long firstOcurence : JSONReaderWriter.allCitiesId.keySet()) {
//                cityID = firstOcurence;
//                JSONReaderWriter.allCitiesId.remove(firstOcurence);
//                break;
//            }
//
//            City city = new City();
//            city.setId(cityID);
//            city.setName(cityName);
//            city.setCountry(country);
//            city.setPopulation(population);
//            city.setCoord(coord);
//
//            filler.fillDatabase(city);
//
//            return "City succsessfully added!";
//        } else {
//            return "City with the name " + cityName + " already exists!";
//        }
//    }
//}

package com.gce.weatherforecastapp.service;

import com.gce.weatherforecastapp.DAO.CitiesRepository;
import com.gce.weatherforecastapp.helpers.CityBasicInfo;
import com.gce.weatherforecastapp.helpers.DatabaseFiller;
import com.gce.weatherforecastapp.helpers.JSONReaderWriter;
import com.gce.weatherforecastapp.model.City;
import com.gce.weatherforecastapp.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityCollector {

    @Autowired
    private DatabaseFiller filler;
    @Autowired
    private CitiesRepository repo;

    public String addCity(CityBasicInfo cityBasicInfo) {

        String cityName = cityBasicInfo.getName();

        if (repo.findByName(cityName) == null) {

            String country = cityBasicInfo.getCountry();
            Long population = cityBasicInfo.getPopulation();
            Coordinates coord = cityBasicInfo.getCoord();
            Long cityID = null;


            for (Long firstOcurence : JSONReaderWriter.allCitiesId.keySet()) {
                cityID = firstOcurence;
                JSONReaderWriter.allCitiesId.remove(firstOcurence);
                break;
            }

            City city = new City();
            city.setId(cityID);
            city.setName(cityName);
            city.setCountry(country);
            city.setPopulation(population);
            city.setCoord(coord);

            filler.fillDatabase(city);

            return "City succsessfully added!";
        } else {
            return "City with the name " + cityName + " already exists!";
        }
    }

    public String updateCity(CityBasicInfo basicInfo) {
        String message = null;
        String cityName = basicInfo.getName();
        City city = repo.findByName(cityName);

        if (city == null) {
            message = "There is no city " + cityName + "!" + " please add  this city first!";
        } else {
            city.setCountry(basicInfo.getCountry());
            city.setPopulation(basicInfo.getPopulation());
            city.setCoord(basicInfo.getCoord());

            repo.save(city);
            message = "City " + cityName + " successfully updated!";
        }
        return message;
    }
}

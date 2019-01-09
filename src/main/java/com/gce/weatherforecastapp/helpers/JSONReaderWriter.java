//package com.gce.weatherforecastapp.helpers;
//
//
//import com.gce.weatherforecastapp.DAO.CitiesRepository;
//import com.gce.weatherforecastapp.model.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.io.*;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class JSONReaderWriter {
//
//    @Autowired
//    private DatabaseFiller filler;
//    @Autowired
//    private CitiesRepository repo;
//    public static Map<Long, Long> allCitiesId;
//    JSONArray jsonArray = null;
//
//    @PostConstruct
//    public void init() throws IOException {
//
//        JSONParser jsonParser = new JSONParser();
//        JSONArray array = null;
//        Long mapValue = 1L;
//        allCitiesId = new ConcurrentHashMap<>();
//
//        InputStream inputStream = new ClassPathResource("city.list.json").getInputStream();
//
//        try {
//
//            array = (JSONArray) jsonParser.parse(new InputStreamReader(inputStream));
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            inputStream.close();
//        }
//
//        for (Object object : array) {
//            JSONObject jsonO = (JSONObject) object;
//
//            Long cityId = (Long) jsonO.get("id");
//
//            allCitiesId.put(cityId, mapValue);
//            mapValue++;
//        }
//
//
//        JSONParser parser = new JSONParser();
////        JSONArray jsonArray = null;
//
//        InputStream jInputStream = new ClassPathResource("Cities.json").getInputStream();
//
//        try {
//            jsonArray = (JSONArray) parser.parse(new InputStreamReader(jInputStream));
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            jInputStream.close();
//        }
//
//        for (Object o : jsonArray) {
//            JSONObject jsonObject = (JSONObject) o;
//
//            Long id = (Long) jsonObject.get("id");
//            System.out.println(id);
//
//
//            for (Long key : allCitiesId.keySet()) {
//                if (allCitiesId.containsKey(id)) {
//                    allCitiesId.remove(key);
//                }
//            }
//
//            String name = (String) jsonObject.get("name");
//            System.out.println(name);
//
//
//            String country = (String) jsonObject.get("country");
//            System.out.println(country);
//
//            Long population = (Long) jsonObject.get("population");
//            System.out.println(population);
//
//            JSONObject coord = (JSONObject) jsonObject.get("coord");
//            Double lat = (Double) coord.get("lat");
//            Double lon = (Double) coord.get("lon");
//
//            City city = new City();
//            city.setId(id);
//            city.setName(name);
//
//            city.setCountry(country);
//            city.setPopulation(population);
//
//            Coordinates coords = new Coordinates();
//            coords.setLat(lat);
//            coords.setLon(lon);
//
//            city.setCoord(coords);
//
//            filler.fillDatabase(city);
//
//
//        }
//    }
//
////    public void saveToJSON() throws IOException {
////
////        System.out.println("Desproying!");
////
////        JSONArray array = new JSONArray();
////
////        for (City c : repo.findAll()) {
////
////            JSONObject object = new JSONObject();
////            object.put("id", c.getId());
////            object.put("name", c.getName());
////            object.put("country", c.getCountry());
////            object.put("population", c.getPopulation());
////
////            JSONObject coord = new JSONObject();
////            coord.put("lat", c.getCoord().getLat());
////            coord.put("lon", c.getCoord().getLon());
////
////            object.put("coord", coord);
////
////            array.add(object);
////        }
////
////        FileOutputStream file = new FileOutputStream("src/main/resources/newJSON.json");
////        OutputStream outputStream = new BufferedOutputStream(file);
////        outputStream.write(array.toJSONString().getBytes());
////        outputStream.close();
////
////
////    }
//public void saveToJSON(City c) throws IOException {
//
//
//
//        JSONObject object = new JSONObject();
//        object.put("id", c.getId());
//        object.put("name", c.getName());
//        object.put("country", c.getCountry());
//        object.put("population", c.getPopulation());
//
//        JSONObject coord = new JSONObject();
//        coord.put("lat", c.getCoord().getLat());
//        coord.put("lon", c.getCoord().getLon());
//
//        object.put("coord", coord);
//
//        jsonArray.add(object);
//
//
//
//    FileOutputStream file = new FileOutputStream("src/main/resources/Cities.json");
//    OutputStream outputStream = new BufferedOutputStream(file);
//    outputStream.write(jsonArray.toJSONString().getBytes());
//    outputStream.close();
//
//
//}
//}

package com.gce.weatherforecastapp.helpers;


import com.gce.weatherforecastapp.DAO.CitiesRepository;
import com.gce.weatherforecastapp.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JSONReaderWriter {

    @Autowired
    private DatabaseFiller filler;
    @Autowired
    private CitiesRepository repo;
    public static Map<Long, Long> allCitiesId;
    JSONArray jsonArray = null;
    JSONParser jsonParser = null;

    @PostConstruct
    public void init() throws IOException {

        Long mapValue = 1L;
        allCitiesId = new ConcurrentHashMap<>();

        InputStream inputStream = new ClassPathResource("city.list.json").getInputStream();

        for (Object object : readFromJSON(inputStream)) {

            JSONObject jsonO = (JSONObject) object;

            Long cityId = (Long) jsonO.get("id");

            allCitiesId.put(cityId, mapValue);
            mapValue++;
        }


        InputStream jInputStream = new ClassPathResource("Cities.json").getInputStream();


        for (Object o : readFromJSON(jInputStream)) {
            JSONObject jsonObject = (JSONObject) o;

            Long id = (Long) jsonObject.get("id");

            for (Long key : allCitiesId.keySet()) {
                if (allCitiesId.containsKey(id)) {
                    allCitiesId.remove(key);
                }
            }

            String name = (String) jsonObject.get("name");

            String country = (String) jsonObject.get("country");

            Long population = (Long) jsonObject.get("population");

            JSONObject coord = (JSONObject) jsonObject.get("coord");
            Double lat = (Double) coord.get("lat");
            Double lon = (Double) coord.get("lon");

            City city = new City();
            city.setId(id);
            city.setName(name);

            city.setCountry(country);
            city.setPopulation(population);

            Coordinates coords = new Coordinates();
            coords.setLat(lat);
            coords.setLon(lon);

            city.setCoord(coords);

            filler.fillDatabase(city);
        }
    }

    public JSONArray readFromJSON(InputStream stream) throws IOException {

        jsonParser = new JSONParser();
        jsonArray = new JSONArray();
        try {
            jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(stream));

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
        return jsonArray;
    }
}


package com.gce.weatherforecastapp.DAO;

import com.gce.weatherforecastapp.model.City;
import com.gce.weatherforecastapp.model.Coordinates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends CrudRepository<City, Long> {

    City findByName(String name);

    List<City> findAllByCountry(String country);

    List<City> findByPopulationBetween(Long from, Long to);

  }

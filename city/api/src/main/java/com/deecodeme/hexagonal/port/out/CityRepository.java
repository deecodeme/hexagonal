package com.deecodeme.hexagonal.port.out;

import com.deecodeme.hexagonal.domain.City;

import java.util.List;

public interface CityRepository {
    City save(City city);
    List<City> getAllCities();
}

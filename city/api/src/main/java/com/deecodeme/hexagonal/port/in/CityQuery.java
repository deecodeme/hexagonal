package com.deecodeme.hexagonal.port.in;

import com.deecodeme.hexagonal.domain.City;

import java.util.List;

public interface CityQuery {
    List<City> getAllValidCities();
}

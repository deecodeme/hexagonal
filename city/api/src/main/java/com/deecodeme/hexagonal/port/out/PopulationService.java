package com.deecodeme.hexagonal.port.out;

import com.deecodeme.hexagonal.domain.City;

import java.util.Optional;

public interface PopulationService {
    Optional<City.CityPopulation> forCity(final City.CityName cityName);
}

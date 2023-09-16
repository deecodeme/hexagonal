package com.deecodeme.hexagonal.application.service;

import com.deecodeme.hexagonal.application.port.in.CityQuery;
import com.deecodeme.hexagonal.application.port.in.CreateCityCommand;
import com.deecodeme.hexagonal.application.port.in.CreateCityUseCase;
import com.deecodeme.hexagonal.application.port.out.CityRepository;
import com.deecodeme.hexagonal.application.port.out.PopulationService;
import com.deecodeme.hexagonal.domain.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CityService implements CreateCityUseCase, CityQuery {
    private final CityRepository cityRepository;
    private final PopulationService populationService;

    public CityService(final CityRepository cityRepository, final PopulationService populationService) {
        this.cityRepository = cityRepository;
        this.populationService = populationService;
    }

    public City process(final CreateCityCommand createCityCommand) {
        // Validation
//        if (city.getId() != null) {
//            throw new IllegalArgumentException("City (" + city.getName() + ") can't be created with a predefined ID");
//        }
        // External service call
        final Optional<City.CityPopulation> population = populationService.forCity(createCityCommand.getCityName());
        // Enrichment
        final City enrichedCity = City.of(createCityCommand.getCityName(), population.orElse(null));
        // Storing in repository
        return cityRepository.save(enrichedCity);
    }

    public List<City> getAllValidCities() {
        // Retrieve from repository
        final List<City> storedCities = cityRepository.getAllCities();
        // Remove those with empty population, and sort them alphabetically
        return storedCities.stream()
                // Comment only the following line to check how SoftAssertions work
                .filter(city -> city.getPopulation() != null)
                .sorted(Comparator.comparing(c -> c.getName().getName()))
                .collect(Collectors.toList());
    }
}

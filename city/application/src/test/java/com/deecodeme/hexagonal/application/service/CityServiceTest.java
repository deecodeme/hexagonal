package com.deecodeme.hexagonal.application.service;

import com.deecodeme.hexagonal.domain.City;
import com.deecodeme.hexagonal.port.in.CreateCityCommand;
import com.deecodeme.hexagonal.port.out.CityRepository;
import com.deecodeme.hexagonal.port.out.PopulationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    @Mock
    private CityRepository cityRepository;
    @Mock
    private PopulationService populationService;
    @InjectMocks
    private CityService cityServiceUnderTest;


    @Test
    void createCity() {
        final City.CityName BLR = City.CityName.of("BLR");
        final CreateCityCommand createCityCommand = new CreateCityCommand(BLR);
        final City.CityPopulation BLR_POPULATION = City.CityPopulation.of(1000000);

        // given
        given(populationService.forCity(BLR)).willReturn(Optional.of(BLR_POPULATION));
        given(cityRepository.save(any(City.class))).willAnswer(answer -> (City) answer.getArgument(0));

        // when
        City city = cityServiceUnderTest.process(createCityCommand);

        // then
        then(city.getPopulation()).as("Check that population is same as the one returned by the population service")
                .isEqualTo(BLR_POPULATION);
        then(city.getName()).as("Check that city name is same as the one passed in the command")
                .isEqualTo(BLR);
    }
}
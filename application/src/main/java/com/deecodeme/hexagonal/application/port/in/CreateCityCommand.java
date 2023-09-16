package com.deecodeme.hexagonal.application.port.in;

import com.deecodeme.hexagonal.ddd.Command;
import com.deecodeme.hexagonal.domain.City;

public class CreateCityCommand implements Command {
    private final City.CityName cityName;

    public CreateCityCommand(final City.CityName cityName) {
        assert cityName != null;
        this.cityName = cityName;
    }

    public City.CityName getCityName() {
        return cityName;
    }
}

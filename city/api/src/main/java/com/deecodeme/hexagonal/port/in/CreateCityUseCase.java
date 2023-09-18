package com.deecodeme.hexagonal.port.in;

import com.deecodeme.hexagonal.domain.City;

public interface CreateCityUseCase {
    City process(final CreateCityCommand createCityCommand);
}

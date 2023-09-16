package com.deecodeme.hexagonal.domain;

import com.deecodeme.hexagonal.ddd.Entity;
import com.deecodeme.hexagonal.ddd.ValueObject;

import java.util.UUID;

public class City extends Entity {
    private final CityName name;
    private final CityPopulation population;

    private City(final String id, final CityName name, final CityPopulation population) {
        super(id);
        this.name = name;
        this.population = population;
    }

    public static City of(final CityName name, final CityPopulation population) {
        assert name != null;
        assert population != null;
        return new City(UUID.randomUUID().toString(), name, population);
    }

    public CityName getName() {
        return name;
    }

    public CityPopulation getPopulation() {
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!name.equals(city.name)) return false;
        return population.equals(city.population);
    }

    public static class CityName implements ValueObject {
        private final String name;

        private CityName(final String name) {
            this.name = name;
        }

        public static CityName of(final String name) {
            return new CityName(name);
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CityName cityName = (CityName) o;

            return name.equals(cityName.name);
        }
    }

    public static class CityPopulation implements ValueObject {
        private final int population;

        private CityPopulation(final int population) {
            this.population = population;
        }

        public static CityPopulation of(final int population) {
            return new CityPopulation(population);
        }

        public int getPopulation() {
            return population;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CityPopulation)) return false;

            CityPopulation that = (CityPopulation) o;

            return population == that.population;
        }
    }
}

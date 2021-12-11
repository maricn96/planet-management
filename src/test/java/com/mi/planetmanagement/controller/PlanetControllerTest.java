package com.mi.planetmanagement.controller;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.service.PlanetService;
import com.mi.planetmanagement.service.SatelliteService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlanetControllerTest {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private SatelliteService satelliteService;

    private List<Satellite> satellites;

    @Before
    private void setup() {
        Satellite satellite1 = Satellite.builder()
                .name("Moon")
                .surfaceArea(321l)
                .mass(321321l)
                .naturalSatellite(true)
                .build();

        satellites.add(satellite1);

    }

    @Test
    @Order(1)
    void savePlanetTest() {
        Planet planet = Planet.builder()
                .name("Earth")
                .surfaceArea(321321l)
                .mass(321321l)
                .distanceFromSun(32131212l)
                .averageSurfaceTemperature(15)
                .satellites(satellites)
                .build();

        planetService.save(planet);

        Assertions.assertThat(planet.getId()).isGreaterThan(0);
    }

    @Test
    void findAllFilteredByName() {
    }

    @Test
    void findAllSortedBySatellites() {
    }
}
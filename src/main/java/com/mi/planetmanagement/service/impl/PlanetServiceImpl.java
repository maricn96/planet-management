package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.dto.PlanetsSortedBySatDTO;
import com.mi.planetmanagement.exceptions.PlanetNotFoundException;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.repository.PlanetRepository;
import com.mi.planetmanagement.repository.SatelliteRepository;
import com.mi.planetmanagement.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private SatelliteRepository satelliteRepository;

    private final String exceptionMessage = "Could not find planet with id: ";


    @Override
    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    @Override
    public Planet findById(Long id) {
        Optional<Planet> planet = planetRepository.findById(id);
        return planet.orElseThrow(() -> new PlanetNotFoundException(exceptionMessage + id));
    }

    @Override
    public void save(Planet entity) {
        planetRepository.save(entity);
    }

    @Override
    public Planet update(Long id, Planet entity) {

        if(!planetRepository.findById(id).isPresent())
            throw new PlanetNotFoundException(exceptionMessage + id);
        else {
            entity.setId(id);
            return planetRepository.save(entity);
        }
    }

    @Override
    public void deleteById(Long id) {
        planetRepository.findById(id).orElseThrow(() -> new PlanetNotFoundException(exceptionMessage + id));
        planetRepository.deleteById(id);
    }

    @Override
    public List<Planet> findAllFilteredByName(Integer perPage, Integer pageNumber, String filterPlanetName) {
        Pageable pageable = PageRequest.of(pageNumber, perPage);
        Page<Planet> planets = planetRepository.findAllFilteredByName(pageable, filterPlanetName);
        return planets.getContent();
    }

    @Override
    public List<Planet> findAllSortedBySatellites(Integer perPage, Integer pageNumber, String direction) {
        Pageable pageable = PageRequest.of(pageNumber, perPage, direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "satellitesNumber");
        Page<PlanetsSortedBySatDTO> planets = planetRepository.findAllSortedBySatelliteNumber(pageable);
        List<Planet> retVal = new ArrayList<>();
        planets.stream()
                .forEach(planet -> {
                    retVal.add(planet.getPlanet());
                });
        return retVal;
    }

}

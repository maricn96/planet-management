package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.dto.PlanetsSortedBySatDTO;
import com.mi.planetmanagement.exceptions.PlanetNotFoundException;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.repository.PlanetRepository;
import com.mi.planetmanagement.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    private final String exceptionMessage = "Could not find planet with id: %d";


    @Override
    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    @Override
    public Planet findById(Long id) {
        return planetRepository.findById(id).orElseThrow(() -> new PlanetNotFoundException(String.format(exceptionMessage, id)));
    }

    @Override
    public void save(Planet entity) {
        planetRepository.save(entity);
    }

    @Override
    public Planet update(Long id, Planet entity) {
        if(!planetRepository.findById(id).isPresent())
            throw new PlanetNotFoundException(String.format(exceptionMessage, id));

        entity.setId(id);
        return planetRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        try {
            planetRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PlanetNotFoundException(String.format(exceptionMessage, id));
        }
    }

    @Override
    public List<Planet> findAllFilteredByName(Integer perPage, Integer pageNumber, String filterPlanetName) {
        Pageable pageable = PageRequest.of(pageNumber, perPage);
        return planetRepository.findAllFilteredByName(pageable, filterPlanetName).getContent();
    }

    @Override
    public List<Planet> findAllSortedBySatellites(Integer perPage, Integer pageNumber, String direction) {
        Pageable pageable = PageRequest.of(pageNumber, perPage, direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "satellitesNumber");
        Page<PlanetsSortedBySatDTO> planets = planetRepository.findAllSortedBySatelliteNumber(pageable);
        return planets.stream()
                .map(planet -> planet.getPlanet()).collect(Collectors.toList());
    }
}

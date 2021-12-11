package com.mi.planetmanagement.service;

import com.mi.planetmanagement.model.Planet;

import java.util.List;

public interface PlanetService {

    List<Planet> findAll();

    Planet findById(Long id);

    void save(Planet entity);

    Planet update(Long id, Planet entity);

    void deleteById(Long id);

    List<Planet> findAllFilteredByName(Integer perPage, Integer pageNumber, String filterPlanetName);

    List<Planet> findAllSortedBySatellites(Integer perPage, Integer pageNumber, String direction);
}

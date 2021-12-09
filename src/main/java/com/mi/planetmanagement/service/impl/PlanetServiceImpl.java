package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.PlanetsSortedBySatDTO;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
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
import java.util.stream.Collectors;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private SatelliteRepository satelliteRepository;


    @Override
    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    @Override
    public Planet findById(Long id) {
        Optional<Planet> planet = planetRepository.findById(id);
        if(!planet.isPresent())
            return null;
        else
            return planet.get();
    }

    @Override
    public void save(Planet entity) {
        planetRepository.save(entity);
    }

    @Override
    public Planet update(Long id, Planet entity) {
        if(planetRepository.findById(id).isPresent()) {
            entity.setId(id);
            return planetRepository.save(entity);
        }
        else
            return null;
    }

    @Override
    public void deleteById(Long id) {
        planetRepository.deleteById(id);
    }

    @Override
    public Planet oneWithSatellites(Long id) {
        List<Satellite> satellites = satelliteRepository.findAllByPlanetId(id);
        Optional<Planet> planetOptional = planetRepository.findById(id);
        if(planetOptional.isPresent()) {
            Planet planet = planetOptional.get();
            planet.setSatellites(satellites);
            return planet;
        } else {
            return null;
        }
    }

    @Override
    public List<Planet> findAllFilteredByName(Integer perPage, Integer pageNumber, String filterPlanetName) {
        Pageable pageable = PageRequest.of(pageNumber, perPage);
        Page<Planet> planets = planetRepository.findAllFiltered(pageable, filterPlanetName);
        return planets.getContent();
    }

    @Override
    public List<Planet> findAllSortedBySatellites(Integer perPage, Integer pageNumber, String direction) {
        Pageable pageable = PageRequest.of(pageNumber, perPage, direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "satellitesNumber");
        Page<PlanetsSortedBySatDTO> planets = planetRepository.findAllSortedBySatelliteNumber(pageable);
        List<Planet> planetsWithSatellites = new ArrayList<>();
        List<Planet> allPlanets = planetRepository.findAll();
        List<Planet> finalSortered = new ArrayList<>();
        for(PlanetsSortedBySatDTO dto : planets) {
            planetsWithSatellites.add(dto.getPlanetDTO());
        }

        List<Planet> planetsWithoutSatellites = allPlanets
                .stream()
                .filter(planet ->
                    planet.getSatellites().size() == 0
                ).collect(Collectors.toList());

        if(direction.equals("ASC")) {
            finalSortered.addAll(planetsWithoutSatellites);
            finalSortered.addAll(planetsWithSatellites);
        } else {
            finalSortered.addAll(planetsWithSatellites);
            finalSortered.addAll(planetsWithoutSatellites);
        }
        return finalSortered;
    }
}

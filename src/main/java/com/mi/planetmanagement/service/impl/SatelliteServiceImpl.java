package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.exceptions.PlanetNotFoundException;
import com.mi.planetmanagement.exceptions.SatelliteNotFoundException;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.repository.SatelliteRepository;
import com.mi.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteServiceImpl implements SatelliteService {

    @Autowired
    private SatelliteRepository satelliteRepository;

    private final String exceptionMessage = "Could not find satellite with id: %d";
    private final String planetExceptionMessage = "Could not find planet with id: %d";


    @Override
    public List<Satellite> findAll() {
        return satelliteRepository.findAll();
    }

    @Override
    public Satellite findById(Long id) {
        return satelliteRepository.findById(id).orElseThrow(() -> new SatelliteNotFoundException(String.format(exceptionMessage, id)));
    }

    @Override
    public void save(Satellite entity) {
        try {
            satelliteRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new PlanetNotFoundException(String.format(planetExceptionMessage, entity.getPlanet().getId()));
        }
    }

    @Override
    public Satellite update(Long id, Satellite entity) {
        if(!satelliteRepository.findById(id).isPresent())
            throw new SatelliteNotFoundException(String.format(exceptionMessage, id));

        entity.setId(id);
        return satelliteRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        try {
            satelliteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new SatelliteNotFoundException(String.format(exceptionMessage, id));        }
    }

    @Override
    public List<Satellite> findAllByPlanetId(Long id) {
        return satelliteRepository.findAllByPlanetId(id);
    }
}

package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.repository.SatelliteRepository;
import com.mi.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatelliteServiceImpl implements SatelliteService {

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Override
    public List<Satellite> findAll() {
        return satelliteRepository.findAll();
    }

    @Override
    public Satellite findById(Long id) {
        return satelliteRepository.findById(id).get();
    }

    @Override
    public void save(Satellite entity) {
        satelliteRepository.save(entity);
    }

    @Override
    public Satellite update(Long id, Satellite entity) {
        if(satelliteRepository.findById(id).isPresent()) {
            entity.setId(id);
            return satelliteRepository.save(entity);
        }
        else
            return null;
    }

    @Override
    public void deleteById(Long id) {
        satelliteRepository.deleteById(id);
    }

    @Override
    public List<Satellite> findAllByPlanetId(Long id) {
        return satelliteRepository.findAllByPlanetId(id);
    }
}

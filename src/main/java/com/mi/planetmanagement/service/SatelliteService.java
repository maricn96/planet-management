package com.mi.planetmanagement.service;

import com.mi.planetmanagement.model.Satellite;

import java.util.List;

public interface SatelliteService {

    List<Satellite> findAll();

    Satellite findById(Long id);

    void save(Satellite entity);

    Satellite update(Long id, Satellite entity);

    void deleteById(Long id);

    List<Satellite> findAllByPlanetId(Long id);
}

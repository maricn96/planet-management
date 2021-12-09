package com.mi.planetmanagement.service;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.model.Planet;

import java.util.List;

public interface PlanetService {

    List<PlanetDTO> findAll();

    PlanetDTO findById(Long id);

    PlanetDTO save(PlanetDTO dto);

    PlanetDTO update(PlanetDTO dto);

    PlanetDTO delete(Long id);
}

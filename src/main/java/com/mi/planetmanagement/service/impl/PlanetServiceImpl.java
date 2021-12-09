package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.mapper.PlanetMapper;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.repository.PlanetRepository;
import com.mi.planetmanagement.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private PlanetMapper planetMapper;


    @Override
    public List<PlanetDTO> findAll() {
        return null;
    }

    @Override
    public PlanetDTO findById(Long id) {
        return null;
    }

    @Override
    public PlanetDTO save(PlanetDTO dto) {
        Planet retVal = planetRepository.save(planetMapper.toEntity(dto));
        return planetMapper.toDto(retVal);
    }

    @Override
    public PlanetDTO update(PlanetDTO dto) {
        return null;
    }

    @Override
    public PlanetDTO delete(Long id) {
        return null;
    }
}

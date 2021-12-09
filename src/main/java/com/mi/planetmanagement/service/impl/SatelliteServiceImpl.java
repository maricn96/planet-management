package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.mapper.JpaContext;
import com.mi.planetmanagement.mapper.PlanetMapper;
import com.mi.planetmanagement.mapper.SatelliteMapper;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.repository.SatelliteRepository;
import com.mi.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SatelliteServiceImpl implements SatelliteService {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mi.planetmanagement");

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Autowired
    private SatelliteMapper satelliteMapper;

//    EntityManager entityManager;

//    public EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }

//    public SatelliteServiceImpl() {
//        this.satelliteMapper = SatelliteMapper.INSTANCE;
//    }

//    JpaContext jpaContext = new JpaContext(getEntityManager());

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

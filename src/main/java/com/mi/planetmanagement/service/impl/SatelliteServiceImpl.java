package com.mi.planetmanagement.service.impl;

import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.mapper.JpaContext;
import com.mi.planetmanagement.mapper.PlanetMapper;
import com.mi.planetmanagement.mapper.SatelliteMapper;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.repository.SatelliteRepository;
import com.mi.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

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
    public void save(SatelliteDTO dto) {
        Satellite sat = satelliteMapper.toEntity(dto);
        satelliteRepository.save(satelliteMapper.toEntity(dto));
    }
}

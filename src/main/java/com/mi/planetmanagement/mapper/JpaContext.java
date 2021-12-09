package com.mi.planetmanagement.mapper;

import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaContext {

//    private EntityManager entityManager;
//
//    private Planet parentEntity;
//
//    public JpaContext(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @BeforeMapping
//    public void setEntity(@MappingTarget Planet parentEntity) {
//        this.parentEntity = parentEntity;
//        entityManager.find(Planet.class, 1l);
//    }
//
//    @AfterMapping
//    public void establishRelation(@MappingTarget Satellite childEntity) {
//        childEntity.setPlanet(parentEntity);
//    }
}

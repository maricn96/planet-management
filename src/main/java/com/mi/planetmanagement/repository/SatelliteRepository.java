package com.mi.planetmanagement.repository;

import com.mi.planetmanagement.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {

    @Query(value = "SELECT s FROM Satellite s WHERE planet.id = :id")
    List<Satellite> findAllByPlanetId(@RequestParam("id") Long id);

}

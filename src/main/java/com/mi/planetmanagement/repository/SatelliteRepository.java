package com.mi.planetmanagement.repository;

import com.mi.planetmanagement.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {

    @Modifying
    @Query("DELETE FROM Satellite s WHERE s.id = :id")
    void deleteById(@Param("id") Long id);

    List<Satellite> findAllByPlanetId(Long id);

}

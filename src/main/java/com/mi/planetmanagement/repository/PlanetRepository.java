package com.mi.planetmanagement.repository;

import com.mi.planetmanagement.dto.PlanetsSortedBySatDTO;
import com.mi.planetmanagement.model.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlanetRepository extends JpaRepository<Planet, Long> {

    @Query(value = "SELECT p FROM Planet p WHERE p.name = :filteredPlanetName")
    Page<Planet> findAllFilteredByName(Pageable pageable, @Param("filteredPlanetName") String filteredPlanetName);

    @Query(value = "SELECT new com.mi.planetmanagement.dto.PlanetsSortedBySatDTO(count(s.id) as satellitesNumber, p) FROM Planet p LEFT JOIN Satellite s ON p.id = s.planet.id GROUP BY p")
    Page<PlanetsSortedBySatDTO> findAllSortedBySatelliteNumber(Pageable pageable);

}

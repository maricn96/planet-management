package com.mi.planetmanagement.repository;

import com.mi.planetmanagement.dto.PlanetsSortedBySatDTO;
import com.mi.planetmanagement.model.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlanetRepository extends JpaRepository<Planet, Long> {

    @Query(value = "SELECT p FROM Planet p WHERE p.name = :filteredPlanetName")
    Page<Planet> findAllFiltered(Pageable pageable, @Param("filteredPlanetName") String filteredPlanetName);

//    @Query(value = "SELECT new com.mi.planetmanagement.dto.PlanetsSortedBySatDTO(count(s) AS satellitesCount, p) FROM Planet p JOIN Satellite s ON p.id = s.planet.id GROUP BY p")
//    Page<PlanetsSortedBySatDTO> findAllSortedBySatelliteNumber(Pageable pageable);

    @Query(value = "SELECT new com.mi.planetmanagement.dto.PlanetsSortedBySatDTO(count(s) as satellitesNumber, p) FROM Planet p JOIN p.satellites s GROUP BY p") //FROM Planet p JOIN Satellite s ON p.id = s.planet.id GROUP BY p
    Page<PlanetsSortedBySatDTO> findAllSortedBySatelliteNumber(Pageable pageable);

//    @Query(value = "SELECT p FROM Planet p JOIN p.satellites s GROUP BY p") //FROM Planet p JOIN Satellite s ON p.id = s.planet.id GROUP BY p
//    Page<Planet> findAllSortedBySatelliteNumber(Pageable pageable);

}

package com.mi.planetmanagement.repository;

import com.mi.planetmanagement.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
}

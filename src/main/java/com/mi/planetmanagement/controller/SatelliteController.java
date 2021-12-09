package com.mi.planetmanagement.controller;

import com.mi.planetmanagement.api.SatelliteApi;
import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.service.PlanetService;
import com.mi.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/satellite")
public class SatelliteController implements SatelliteApi {

    @Autowired
    private SatelliteService satelliteService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody SatelliteDTO dto) {
        satelliteService.save(dto);
        return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
    }
}

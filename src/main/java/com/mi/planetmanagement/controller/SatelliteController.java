package com.mi.planetmanagement.controller;

import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.exceptions.SatelliteNotFoundException;
import com.mi.planetmanagement.mapper.SatelliteMapper;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/satellite")
public class SatelliteController {

    @Autowired
    private SatelliteService satelliteService;

    @Autowired
    private SatelliteMapper satelliteMapper;

    @GetMapping
    public ResponseEntity<List<SatelliteDTO>> findAll() {
        List<Satellite> retList = satelliteService.findAll();
        return new ResponseEntity<List<SatelliteDTO>>(satelliteMapper.toListDTO(retList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SatelliteDTO> findById(@PathVariable Long id) {
        Satellite ret = satelliteService.findById(id);
        return new ResponseEntity<SatelliteDTO>(satelliteMapper.toDTO(ret), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody SatelliteDTO dto) {
        satelliteService.save(satelliteMapper.toEntity(dto));
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteDTO> update(@PathVariable Long id, @RequestBody SatelliteDTO dto) {
        Satellite ret = satelliteService.update(id, satelliteMapper.toEntity(dto));
        if(ret == null)
            throw new SatelliteNotFoundException("No satellite with id " + id + " found!");
        else
            return new ResponseEntity<SatelliteDTO>(satelliteMapper.toDTO(ret), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        satelliteService.deleteById(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @GetMapping("/findAllByPlanetId/{id}")
    public ResponseEntity<List<SatelliteDTO>> findAllByPlanetId(@PathVariable Long id) {
        List<Satellite> retList = satelliteService.findAllByPlanetId(id);
        return new ResponseEntity<List<SatelliteDTO>>(satelliteMapper.toListDTO(retList), HttpStatus.OK);
    }

}

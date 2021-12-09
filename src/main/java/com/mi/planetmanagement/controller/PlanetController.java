package com.mi.planetmanagement.controller;

import com.mi.planetmanagement.api.PlanetApi;
import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.exceptions.NoRecordFoundException;
import com.mi.planetmanagement.mapper.PlanetMapper;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/planet")
public class PlanetController implements PlanetApi {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PlanetMapper planetMapper;

    @GetMapping
    public ResponseEntity<List<PlanetDTO>> findAll() {
        List<Planet> retList = planetService.findAll();
        return new ResponseEntity<List<PlanetDTO>>(planetMapper.toListDTO(retList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> findById(@PathVariable Long id) {
        Planet ret = planetService.findById(id);
        return new ResponseEntity<PlanetDTO>(planetMapper.toDTO(ret), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody PlanetDTO dto) {
        planetService.save(planetMapper.toEntity(dto));
        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetDTO> update(@PathVariable Long id, @RequestBody PlanetDTO dto) {
        Planet ret = planetService.update(id, planetMapper.toEntity(dto));
        if(ret == null)
            throw new NoRecordFoundException("No record with id " + id + " found!");
        else
            return new ResponseEntity<PlanetDTO>(planetMapper.toDTO(ret), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        planetService.deleteById(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @GetMapping("/oneWithSatellites/{id}")
    public ResponseEntity<PlanetDTO> oneWithSatellites(@PathVariable Long id) {
        Planet planet = planetService.oneWithSatellites(id);
        return new ResponseEntity<PlanetDTO>(planetMapper.toDTO(planet), HttpStatus.OK);
    }

    @GetMapping("/findAllFilteredByName/{perPage}/{pageNumber}")
    public ResponseEntity<List<PlanetDTO>> findAllFilteredByName(@PathVariable Integer perPage, @PathVariable Integer pageNumber, @RequestParam String filterPlanetName) {
        List<Planet> retList = planetService.findAllFilteredByName(perPage, pageNumber, filterPlanetName);
        return new ResponseEntity<List<PlanetDTO>>(planetMapper.toListDTO(retList), HttpStatus.OK);
    }

    @GetMapping("/findAllSortedBySatellites/{perPage}/{pageNumber}/{direction}")
    public ResponseEntity<List<PlanetDTO>> findAllSortedBySatellites(@PathVariable Integer perPage, @PathVariable Integer pageNumber, @PathVariable String direction) {
        List<Planet> retList = planetService.findAllSortedBySatellites(perPage, pageNumber, direction);
        return new ResponseEntity<List<PlanetDTO>>(planetMapper.toListDTO(retList), HttpStatus.OK);
    }

}

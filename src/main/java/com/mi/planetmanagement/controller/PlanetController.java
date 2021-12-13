package com.mi.planetmanagement.controller;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.PlanetPageFilterRequestDTO;
import com.mi.planetmanagement.dto.PlanetPageSortRequestDTO;
import com.mi.planetmanagement.mapper.PlanetMapper;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/planet")
public class PlanetController {

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
    public ResponseEntity<String> save(@RequestBody @Valid PlanetDTO dto) {
        planetService.save(planetMapper.toEntity(dto));
        return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetDTO> update(@PathVariable Long id, @RequestBody @Valid PlanetDTO dto) {
        Planet ret = planetService.update(id, planetMapper.toEntity(dto));
        return new ResponseEntity<PlanetDTO>(planetMapper.toDTO(ret), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        planetService.deleteById(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @PostMapping("/findAllFilteredByName")
    public ResponseEntity<List<PlanetDTO>> findAllFilteredByName(@RequestBody PlanetPageFilterRequestDTO dto) {
        List<Planet> retList = planetService.findAllFilteredByName(dto.getPagingRequestDTO().getPerPage(), dto.getPagingRequestDTO().getPageNumber(), dto.getFilterPlanetName());
        return new ResponseEntity<List<PlanetDTO>>(planetMapper.toListDTO(retList), HttpStatus.OK);
    }

    @PostMapping("/findAllSortedBySatellites")
    public ResponseEntity<List<PlanetDTO>> findAllSortedBySatellites(@RequestBody PlanetPageSortRequestDTO dto) {
        List<Planet> retList = planetService.findAllSortedBySatellites(dto.getPagingRequestDTO().getPerPage(), dto.getPagingRequestDTO().getPageNumber(), dto.getDirection());
        return new ResponseEntity<List<PlanetDTO>>(planetMapper.toListDTO(retList), HttpStatus.OK);
    }

}

package com.mi.planetmanagement.controller;

import com.mi.planetmanagement.api.PlanetApi;
import com.mi.planetmanagement.dto.PlanetDTO;
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

    @GetMapping
    public ResponseEntity<List<PlanetDTO>> findAll() {
        return new ResponseEntity<List<PlanetDTO>>(planetService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<PlanetDTO>(planetService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanetDTO> save(@RequestBody PlanetDTO dto) {
        return new ResponseEntity<PlanetDTO>(planetService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetDTO> update(@PathVariable Long id, @RequestBody PlanetDTO dto) {
        return new ResponseEntity<PlanetDTO>(planetService.update(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanetDTO> deleteById(@PathVariable Long id) {
        return new ResponseEntity<PlanetDTO>(planetService.delete(id), HttpStatus.OK);
    }

}

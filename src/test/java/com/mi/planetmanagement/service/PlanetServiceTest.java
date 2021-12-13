package com.mi.planetmanagement.service;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.PlanetsSortedBySatDTO;
import com.mi.planetmanagement.exceptions.PlanetNotFoundException;
import com.mi.planetmanagement.mapper.SatelliteMapper;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
import com.mi.planetmanagement.repository.PlanetRepository;
import com.mi.planetmanagement.service.impl.PlanetServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetServiceImpl planetService;

    private Long existsId;
    private Long notExistsId;
    private Planet planet;
    private Planet updatePlanet;
    Satellite satellite1;
    private List<Planet> planets;
    private List<Satellite> satellites;
    private Pageable pageable;
    private String planetNameExists;
    private String planetNameNotExists;
    private Page<Planet> planetPage;

    Page<PlanetsSortedBySatDTO> planetsSortedBySatDTOPage;
    PlanetsSortedBySatDTO planetsSortedBySatDTO;
    List<PlanetsSortedBySatDTO> planetsSortedBySatDTOList;


    @Before
    public void setup() {

        existsId = 1l;
        notExistsId = 6l;

        planets = new ArrayList<>();
        satellites = new ArrayList<>();
        satellite1 = Satellite.builder()
                .name("Moon")
                .surfaceArea(321l)
                .mass(321321l)
                .naturalSatellite(true)
                .build();
        satellites.add(satellite1);

        planet = Planet.builder()
                .id(existsId)
                .name("Earth")
                .surfaceArea(510100000l)
                .mass(59721900000l)
                .distanceFromSun(93000000l)
                .averageSurfaceTemperature(15)
                .satellites(satellites)
                .build();

        planets.add(planet);

        updatePlanet = planet;
        updatePlanet.setName("Saturn");

        pageable = PageRequest.of(0, 1);
        planetNameExists = "Earth";
        planetNameNotExists = "Uranus";
        planetPage = new PageImpl<Planet>(planets);

        planetsSortedBySatDTO = new PlanetsSortedBySatDTO(5l, planet); //popravi ovo i ona dva poslednja testa
        planetsSortedBySatDTOList = new ArrayList<>();
        planetsSortedBySatDTOList.add(planetsSortedBySatDTO);
        planetsSortedBySatDTOPage = new PageImpl<PlanetsSortedBySatDTO>(planetsSortedBySatDTOList);
    }

    @Test
    public void testFindAllSuccess() {
        when(planetRepository.findAll()).thenReturn(planets);

        List<Planet> response = planetService.findAll();

        assertEquals(planets, response);
        verify(planetRepository, times(1)).findAll();
        verifyNoMoreInteractions(planetRepository);
    }

    @Test
    public void testFindAllNotFound() {
        when(planetRepository.findAll()).thenReturn(null);

        List<Planet> response = planetService.findAll();

        assertEquals(null, response);

        verify(planetRepository, times(1)).findAll();
        verifyNoMoreInteractions(planetRepository);
    }

    @Test
    public void testFindByIdSuccess() {
        when(planetRepository.findById(existsId)).thenReturn(Optional.of(planet));

        Planet response = planetRepository.findById(existsId).get();

        assertEquals(planet, response);
        verify(planetRepository, times(1)).findById(existsId);
        verifyNoMoreInteractions(planetRepository);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void testFindByIdException() {
        when(planetRepository.findById(notExistsId)).thenThrow(PlanetNotFoundException.class);

        planetService.findById(notExistsId);

        verify(planetRepository, times(1)).findById(notExistsId);
        verifyNoMoreInteractions(planetRepository);
    }

    @Test
    public void testUpdateSuccess() {
        when(planetRepository.findById(existsId)).thenReturn(Optional.of(planet));
        when(planetRepository.save(updatePlanet)).thenReturn(updatePlanet);

        Planet response = planetService.update(existsId, updatePlanet);

        assertEquals(updatePlanet, response);
        verify(planetRepository, times(1)).findById(existsId);
        verify(planetRepository, times(1)).save(updatePlanet);
        verifyNoMoreInteractions(planetRepository);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void testUpdateException() {
        when(planetRepository.findById(notExistsId)).thenThrow(PlanetNotFoundException.class);

        planetService.update(notExistsId, updatePlanet);
        verify(planetRepository, times(1)).findById(notExistsId);
        verifyNoMoreInteractions(planetRepository);
    }

    @Test
    public void testDeleteSuccess() {
        Mockito.doNothing().when(planetRepository).deleteById(existsId);
        planetService.deleteById(existsId);

        verify(planetRepository, times(1)).deleteById(existsId);
        verifyNoMoreInteractions(planetRepository);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void testDeleteException() {
        doThrow(new PlanetNotFoundException()).when(planetRepository).deleteById(notExistsId);
        planetService.deleteById(notExistsId);

        verify(planetRepository, times(1)).deleteById(notExistsId);
        verifyNoMoreInteractions(planetRepository);
    }

    @Test
    public void testFindAllFilteredByNameSuccess() {
        when(planetRepository.findAllFilteredByName(pageable, planetNameExists)).thenReturn(planetPage);

        List<Planet> result = planetService.findAllFilteredByName(pageable.getPageSize(), pageable.getPageNumber(), planetNameExists);

        assertEquals(planets, result);
        verify(planetRepository, times(1)).findAllFilteredByName(pageable, planetNameExists);
        verifyNoMoreInteractions(planetRepository);
    }
}

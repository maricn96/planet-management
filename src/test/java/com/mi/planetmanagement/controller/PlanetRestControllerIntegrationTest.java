package com.mi.planetmanagement.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mi.planetmanagement.PlanetmanagementApplication;
import com.mi.planetmanagement.dto.PagingRequestDTO;
import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.PlanetPageFilterRequestDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlanetmanagementApplication.class)
@ComponentScan(basePackages = "com.mi.planetmanagement.")
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class PlanetRestControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Long existsId;
    private String existsName;
    private Long notExistsId;
    private String notExistsName;
    private PlanetPageFilterRequestDTO planetPageFilterRequestDTO;
    private PlanetPageFilterRequestDTO planetPageFilterBadRequestDTO;
    private List<PlanetDTO> planets;
    private PlanetDTO existsPlanet;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();
        existsId = 5l;
        existsName = "Jupiter";
        notExistsId = 8l;
        notExistsName = "Uranus";
        planetPageFilterRequestDTO = PlanetPageFilterRequestDTO.builder()
                .pagingRequestDTO(PagingRequestDTO.builder()
                                    .perPage(2)
                                    .pageNumber(0)
                                    .build())
                .filterPlanetName(existsName)
                .build();

        planetPageFilterBadRequestDTO = PlanetPageFilterRequestDTO.builder()
                .pagingRequestDTO(PagingRequestDTO.builder()
                        .perPage(2)
                        .pageNumber(0)
                        .build())
                .filterPlanetName(notExistsName)
                .build();

        existsPlanet = PlanetDTO.builder()
                .id(existsId)
                .name(existsName)
                .surfaceArea(61000l)
                .mass(18720l)
                .distanceFromSun(48000l)
                .averageSurfaceTemperature(1)
                .satelliteDTOList(new ArrayList<>())
                .build();

        planets = new ArrayList<>();
        planets.add(existsPlanet);
    }

    @Test
    public void testFindAllFilteredByName() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/planet/findAllFilteredByName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsBytes(planetPageFilterRequestDTO)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        List<PlanetDTO> retVal = objectMapper.readValue(result.getResponse().getContentAsByteArray(), new TypeReference<List<PlanetDTO>>() {});

        assertEquals(planets, retVal);
    }
}

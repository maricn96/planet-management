package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDTO {

    private Long id;
    private String name;
    private Long surfaceArea;
    private Long mass;
    private Long distanceFromSun;
    private Integer averageSurfaceTemperature;

    private List<SatelliteDTO> satelliteDTOList;

}

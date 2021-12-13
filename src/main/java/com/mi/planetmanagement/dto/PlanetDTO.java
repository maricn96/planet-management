package com.mi.planetmanagement.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlanetDTO {

    private Long id;
    private String name;
    private Long surfaceArea;
    private Long mass;
    private Long distanceFromSun;
    private Integer averageSurfaceTemperature;
    private List<SatelliteDTO> satelliteDTOList;

}

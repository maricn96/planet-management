package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDTO {

    private Long id;

    @NotNull(message = "name must not be null!")
    private String name;

    @NotNull(message = "surface_area must not be null!")
    private Long surfaceArea;

    @NotNull(message = "mass must not be null!")
    private Long mass;

    @NotNull(message = "distance_from_sun must not be null!")
    private Long distanceFromSun;

    private Integer averageSurfaceTemperature;

    private List<SatelliteDTO> satelliteDTOList;

}

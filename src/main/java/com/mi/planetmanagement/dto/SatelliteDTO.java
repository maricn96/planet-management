package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteDTO {

    private Long id;

    @NotNull(message = "name must not be null!")
    private String name;

    @NotNull(message = "surface_area must not be null!")
    private Long surfaceArea;

    @NotNull(message = "mass must not be null!")
    private Long mass;

    private Boolean naturalSatellite;

    private Long planetId;

}

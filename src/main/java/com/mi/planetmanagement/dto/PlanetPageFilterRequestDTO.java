package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetPageFilterRequestDTO {

    private PagingRequestDTO pagingRequestDTO;
    private String filterPlanetName;

}

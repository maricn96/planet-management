package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetPageFilterRequestDTO {

    private PagingRequestDTO pagingRequestDTO;
    private String filterPlanetName;

}

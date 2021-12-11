package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetPageSortRequestDTO {

    private PagingRequestDTO pagingRequestDTO;
    private String direction;

}

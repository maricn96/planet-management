package com.mi.planetmanagement.dto;

import com.mi.planetmanagement.model.Planet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetsSortedBySatDTO {

    private Long satellitesNumber;
    private Planet planet;

}

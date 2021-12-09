package com.mi.planetmanagement.dto;

import com.mi.planetmanagement.model.Planet;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetsSortedBySatDTO {

    private Long satellitesNumber;
    private Planet planetDTO;



}

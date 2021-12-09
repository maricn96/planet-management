package com.mi.planetmanagement.dto;

import com.mi.planetmanagement.model.Planet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteDTO {

    private Long id;
    private String name;
    private Long surfaceArea;
    private Long mass;
    private Boolean naturalSatellite;
    private Long planetId;

}

package com.mi.planetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Satellite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long surfaceArea;
    private Long mass;
    private Boolean naturalSatellite;

    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

}

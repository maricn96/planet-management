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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "surface_area", nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    @Column(name = "natural_satellite")
    private Boolean naturalSatellite;

    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

}

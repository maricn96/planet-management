package com.mi.planetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "surface_area", nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    @Column(name = "distanceFromSun", nullable = false)
    private Long distanceFromSun;

    @Column(name = "average_surface_temperature")
    private Integer averageSurfaceTemperature;

    @OneToMany(mappedBy = "planet", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Satellite> satellites;
}

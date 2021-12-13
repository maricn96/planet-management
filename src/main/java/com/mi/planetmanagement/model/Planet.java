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

    @Column(nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    @Column(nullable = false)
    private Long distanceFromSun;

    @Column()
    private Integer averageSurfaceTemperature;

    @OneToMany(mappedBy = "planet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Satellite> satellites;
}

package com.mi.planetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long surfaceArea;
    private Long mass;
    private Long distanceFromSun;
    private Integer averageSurfaceTemperature;

    @OneToMany(mappedBy = "planet")
    private List<Satellite> satellites;
}

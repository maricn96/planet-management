package com.mi.planetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
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

    @Column(nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    private Boolean naturalSatellite;

    @ManyToOne
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

}

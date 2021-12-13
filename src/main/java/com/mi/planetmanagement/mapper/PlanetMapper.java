package com.mi.planetmanagement.mapper;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.model.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanetMapper {

    @Mapping(target = "satelliteDTOList", source = "satellites")
    PlanetDTO toDTO(Planet planet);

    @Mapping(target = "satellites", source = "satelliteDTOList")
    Planet toEntity(PlanetDTO planetDTO);

    List<PlanetDTO> toListDTO(List<Planet> planetList);

}

package com.mi.planetmanagement.mapper;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.model.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlanetMapper {

    PlanetMapper INSTANCE = Mappers.getMapper(PlanetMapper.class);

    PlanetDTO toDto(Planet planet);
    Planet toEntity(PlanetDTO planetDTO);
}

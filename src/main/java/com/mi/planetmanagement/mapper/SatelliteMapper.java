package com.mi.planetmanagement.mapper;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PlanetMapper.class})
public interface SatelliteMapper {

    SatelliteMapper INSTANCE = Mappers.getMapper(SatelliteMapper.class);

    @Mapping(target = "planetId", source = "planet.id")
    SatelliteDTO toDto(Satellite satellite);

    @Mapping(target = "planet.id", source = "planetId")
    Satellite toEntity(SatelliteDTO satelliteDTO);
}
package com.mi.planetmanagement.mapper;

import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.model.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SatelliteMapper {

    @Mapping(target = "planetId", source = "planet.id")
    SatelliteDTO toDTO(Satellite satellite);

    @Mapping(target = "planet.id", source = "planetId")
    Satellite toEntity(SatelliteDTO satelliteDTO);

    List<SatelliteDTO> toListDTO(List<Satellite> retList);
}
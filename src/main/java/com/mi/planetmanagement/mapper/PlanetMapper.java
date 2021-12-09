package com.mi.planetmanagement.mapper;

import com.mi.planetmanagement.dto.PlanetDTO;
import com.mi.planetmanagement.dto.SatelliteDTO;
import com.mi.planetmanagement.model.Planet;
import com.mi.planetmanagement.model.Satellite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanetMapper {

    @Mapping(target = "satelliteDTOList", source = "satellites")
    PlanetDTO toDTO(Planet planet);

//    default List<SatelliteDTO> mapLists(List<Satellite> satellites) {
//        List<SatelliteDTO> satelliteDTOList = new ArrayList<>();
//        for(Satellite s : satellites) {
//            satelliteDTOList.add(satelliteMapper.toDTO(s));
//        }
//        return satelliteDTOList;
//    }

    Planet toEntity(PlanetDTO planetDTO);
    List<PlanetDTO> toListDTO(List<Planet> planetList);

}

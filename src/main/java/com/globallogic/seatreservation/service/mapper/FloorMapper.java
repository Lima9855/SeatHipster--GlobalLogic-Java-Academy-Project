package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Building;
import com.globallogic.seatreservation.domain.Dimensions;
import com.globallogic.seatreservation.domain.Floor;
import com.globallogic.seatreservation.service.dto.BuildingDto;
import com.globallogic.seatreservation.service.dto.DimensionsDto;
import com.globallogic.seatreservation.service.dto.FloorDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FloorMapper extends EntityMapper<FloorDto, Floor> {
    @Mapping(target = "dimensions", source = "dimensions", qualifiedByName = "toDtoDimensionsId")
    @Mapping(target = "building", source = "building", qualifiedByName = "toDtoBuildingId")
    FloorDto toDto(Floor s);

    @Named("toDtoDimensionsId")
    DimensionsDto toDtoDimensionsId(Dimensions dimensions);

    @Named("toDtoBuildingId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    BuildingDto toDtoBuildingId(Building building);
}

package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Address;
import com.globallogic.seatreservation.domain.Building;
import com.globallogic.seatreservation.domain.Location;
import com.globallogic.seatreservation.service.dto.AddressDto;
import com.globallogic.seatreservation.service.dto.BuildingDto;
import com.globallogic.seatreservation.service.dto.LocationDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BuildingMapper extends EntityMapper<BuildingDto, Building> {
    @Mapping(target = "address", source = "address", qualifiedByName = "toDtoAddressId")
    @Mapping(target = "location", source = "location", qualifiedByName = "toDtoLocationId")
    BuildingDto toDto(Building s);

    @Named("toDtoAddressId")
    AddressDto toDtoAddressId(Address address);

    @Named("toDtoLocationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LocationDto toDtoLocationId(Location location);
}



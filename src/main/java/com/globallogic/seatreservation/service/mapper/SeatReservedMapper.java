package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.domain.SeatReserved;
import com.globallogic.seatreservation.domain.User;
import com.globallogic.seatreservation.service.dto.SeatReservedDetailsDto;
import com.globallogic.seatreservation.service.dto.SeatReservedDto;
import com.globallogic.seatreservation.service.dto.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SeatReservedMapper extends EntityMapper<SeatReservedDto, SeatReserved> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "seatId", source = "seat.id")
    SeatReservedDto toDto(SeatReserved s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDto toDtoUserId(User user);

    @Mapping(target = "seat.id", source = "seatId")
    SeatReserved toEntity(SeatReservedDto dto);
}

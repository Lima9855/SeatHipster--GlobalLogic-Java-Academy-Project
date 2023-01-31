package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Coordinates;
import com.globallogic.seatreservation.service.dto.CoordinatesDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface CoordinatesMapper extends EntityMapper<CoordinatesDto, Coordinates> {}

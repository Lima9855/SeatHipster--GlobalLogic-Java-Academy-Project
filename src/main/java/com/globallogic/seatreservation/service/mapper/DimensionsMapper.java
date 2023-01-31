package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Dimensions;
import com.globallogic.seatreservation.service.dto.DimensionsDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DimensionsMapper extends EntityMapper<DimensionsDto, Dimensions> {}

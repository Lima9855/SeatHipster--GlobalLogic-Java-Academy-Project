package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.Restrictions;
import com.globallogic.seatreservation.service.dto.RestrictionsDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestrictionsMapper extends EntityMapper<RestrictionsDto, Restrictions> {}

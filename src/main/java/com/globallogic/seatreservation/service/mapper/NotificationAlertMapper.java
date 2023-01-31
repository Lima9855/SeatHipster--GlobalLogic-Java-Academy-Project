package com.globallogic.seatreservation.service.mapper;

import com.globallogic.seatreservation.domain.NotificationAlert;
import com.globallogic.seatreservation.service.dto.NotificationAlertDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationAlertMapper extends EntityMapper<NotificationAlertDto, NotificationAlert> {}

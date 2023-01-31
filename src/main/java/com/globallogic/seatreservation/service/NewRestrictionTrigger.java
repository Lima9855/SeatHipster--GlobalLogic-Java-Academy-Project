package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Restrictions;
import com.globallogic.seatreservation.domain.SeatReserved;
import com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus;
import com.globallogic.seatreservation.repository.SeatRepository;
import com.globallogic.seatreservation.service.dto.SeatDto;
import com.globallogic.seatreservation.service.mapper.SeatMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewRestrictionTrigger {

    private static final String REASON = "Your reservation was cancelled due to restrictions";

    private final RestrictionHandler restrictionHandler;
    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final SeatReservedService seatReservedService;
    private final ReservationService reservationService;


    public NewRestrictionTrigger(RestrictionHandler restrictionHandler,
                                 SeatRepository seatRepository,
                                 SeatMapper seatMapper,
                                 SeatReservedService seatReservedService,
                                 ReservationService reservationService) {
        this.restrictionHandler = restrictionHandler;
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
        this.seatReservedService = seatReservedService;
        this.reservationService = reservationService;
    }

    public void execute(final Restrictions restriction) {
        List<SeatDto> seatsAfterRestrictionButOnlyWithRestricedStatus = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, getAllSeats())
            .stream()
            .filter(seatDto -> seatDto.getAvailabilityStatus().equals(AvailabilityStatus.RESTRICTED))
            .toList();
        List<Long> listOfSeatId = seatsAfterRestrictionButOnlyWithRestricedStatus.stream()
            .map(SeatDto::getId)
            .toList();
        List<SeatReserved> seatReservedsThatWillBeCancelled = seatReservedService.findByReservedSeatsInGivenPeriodOfTime(restriction.getFromDate(), restriction.getToDate(), listOfSeatId);
        seatReservedsThatWillBeCancelled.forEach(seatReserved -> reservationService.cancelReservations(seatReserved, REASON));
    }

    private List<SeatDto> getAllSeats() {
        return seatRepository.findAll()
            .stream()
            .map(seatMapper::toDto)
            .collect(Collectors.toList());
    }

}

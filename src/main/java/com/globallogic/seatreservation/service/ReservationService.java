package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.domain.SeatReserved;
import com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus;
import com.globallogic.seatreservation.domain.enumeration.ReservationStatus;
import com.globallogic.seatreservation.service.dto.SeatDto;
import com.globallogic.seatreservation.service.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final NotificationAlertService notificationAlertService;
    private final SeatReservedService seatReservedService;

    public ReservationService(NotificationAlertService notificationAlertService, SeatReservedService seatReservedService) {
        this.notificationAlertService = notificationAlertService;
        this.seatReservedService = seatReservedService;
    }

    public List<SeatDto> getUpdatedSeatsAfterReservation(List<SeatDto> seats, ZonedDateTime from, ZonedDateTime to) {
        List<Long> seatIds = seats.stream()
            .map(SeatDto::getId)
            .toList();
        List<SeatReserved> seatsThatAreReservedInGivenPeriodOfTime = seatReservedService.findByReservedSeatsInGivenPeriodOfTime(from, to, seatIds);
        List<Long> idOfOccupiedSeats = seatsThatAreReservedInGivenPeriodOfTime.stream()
            .map(SeatReserved::getSeat)
            .map(Seat::getId)
            .toList();
        handleReservation(seats, idOfOccupiedSeats);
        return seats;
    }

    public void handleReservation(List<SeatDto> seats, List<Long> occupiedSeats) {
        seats.forEach(seat -> occupiedReservation(seat, occupiedSeats));
    }

    private void occupiedReservation(SeatDto seat, List<Long> occupiedSeats) {
        if (occupiedSeats.contains(seat.getId())) {
            seat.setAvailabilityStatus(AvailabilityStatus.OCCUPIED);
        }
    }

    public void cancelReservations(SeatReserved seatReserved, String reason) {
        seatReserved.setReservationStatus(ReservationStatus.CANCELLED);
        notificationAlertService.createNewNotificationForUser(seatReserved.getUser(), reason);
    }

}

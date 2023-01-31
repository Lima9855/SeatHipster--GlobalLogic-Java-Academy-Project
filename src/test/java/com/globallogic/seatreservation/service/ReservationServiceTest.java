package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Seat;
import com.globallogic.seatreservation.domain.SeatReserved;
import com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus;
import com.globallogic.seatreservation.service.dto.SeatDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationServiceTest {

    @Mock
    private NotificationAlertService notificationAlertService;

    @Mock
    private SeatReservedService seatReservedService;

    @BeforeAll
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void shouldReturnUpdatedSeatsAfterReservation() {
        // given
        final ReservationService reservationService = new ReservationService(notificationAlertService, seatReservedService);
        final ZonedDateTime from = ZonedDateTime.now();
        final ZonedDateTime to = ZonedDateTime.now();
        final List<SeatDto> seats = List.of(
            createSeat(1L, AvailabilityStatus.FREE),
            createSeat(2L, AvailabilityStatus.FREE),
            createSeat(3L, AvailabilityStatus.UNAVAILABLE),
            createSeat(4L, AvailabilityStatus.FREE)
        );
        final List<SeatReserved> reservedSeat = List.of(
            createSeatReserved(1L),
            createSeatReserved(4L)
        );

        when(seatReservedService.findByReservedSeatsInGivenPeriodOfTime(any(), any(), any())).thenReturn(reservedSeat);

        // when
        final List<SeatDto> updatedSeatsAfterReservation = reservationService.getUpdatedSeatsAfterReservation(seats, from, to);

        // then
        assertEquals(4, updatedSeatsAfterReservation.size());
        assertEquals(AvailabilityStatus.OCCUPIED, findById(updatedSeatsAfterReservation, 1L).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, findById(updatedSeatsAfterReservation, 2L).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.UNAVAILABLE, findById(updatedSeatsAfterReservation, 3L).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.OCCUPIED, findById(updatedSeatsAfterReservation, 4L).getAvailabilityStatus());
    }

    private SeatDto findById(final List<SeatDto> seats, final Long id) {
        return seats.stream()
            .filter(seat -> id.equals(seat.getId()))
            .findFirst()
            .orElseThrow();
    }

    private SeatDto createSeat(final Long id, final AvailabilityStatus availabilityStatus) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(id);
        seatDto.setAvailabilityStatus(availabilityStatus);
        return seatDto;
    }

    private SeatReserved createSeatReserved(final Long seatId) {
        final SeatReserved seatReserved = new SeatReserved();
        final Seat seat = new Seat();
        seat.setId(seatId);
        seatReserved.setSeat(seat);
        return seatReserved;
    }

}

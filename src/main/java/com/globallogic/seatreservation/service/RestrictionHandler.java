package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Restrictions;
import com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus;
import com.globallogic.seatreservation.service.dto.SeatDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus.FREE;
import static java.util.Comparator.comparing;

@Service
public class RestrictionHandler {

    private static final BigDecimal ONE_PLACE_VALUE = BigDecimal.ONE;

    public List<SeatDto> getUpdatedSeatsAfterRestriction(Restrictions restriction, List<SeatDto> seatDtos) {
        switch (restriction.getRestrictionType()){
            case PERCENTAGE -> updateSeatsByPercentage(restriction, seatDtos);
            case PER_SEATS_NUMBER -> updateSeatsPerSeatsNumber(restriction,seatDtos);
        }
        return seatDtos;
    }

    private void updateSeatsPerSeatsNumber(Restrictions restriction, List<SeatDto> seatDtos) {
        seatDtos.stream()
            .filter(seatDto -> isSeatRestrictedByPerSeatsNumber(seatDto, restriction.getRestrictionValue()))
            .forEach(seatDto -> seatDto.setAvailabilityStatus(AvailabilityStatus.RESTRICTED));
    }

    private void updateSeatsByPercentage(Restrictions restriction, List<SeatDto> seatDtos) {
        final List<SeatDto> sortedSeats = getSortedSeats(seatDtos);
        final Map<Long, List<SeatDto>> seatsPerFloors = sortedSeats.stream()
            .collect(Collectors.groupingBy(SeatDto::getFloorIdToCompare));
        seatsPerFloors.values().forEach(seatsPerFloor -> updateSeatsByPercentagePerFloor(restriction, seatsPerFloor));
    }

    private void updateSeatsByPercentagePerFloor(Restrictions restriction, List<SeatDto> seatByFloor) {
        final int restrictedValuePercentage = restriction.getRestrictionValue();
        final BigDecimal restrictedValue = BigDecimal.valueOf(restrictedValuePercentage).divide(BigDecimal.valueOf(100.0));
        BigDecimal currentPerSeat = BigDecimal.ZERO;
        for (SeatDto seat : seatByFloor) {
            currentPerSeat = currentPerSeat.add(restrictedValue);
            if (currentPerSeat.compareTo(ONE_PLACE_VALUE) >= 0) {
                currentPerSeat = currentPerSeat.subtract(ONE_PLACE_VALUE);
            } else if (seat.getAvailabilityStatus() != AvailabilityStatus.UNAVAILABLE) {
                seat.setAvailabilityStatus(AvailabilityStatus.RESTRICTED);
            }
        }
    }

    private List<SeatDto> getSortedSeats(List<SeatDto> seatDtos) {
        return seatDtos.stream()
            .sorted(comparing(SeatDto::getLocationNameToCompare)
                .thenComparing(SeatDto::getBuildingIdToCompare)
                .thenComparing(SeatDto::getFloorNumberToCompare)
                .thenComparing(SeatDto::getSeatNumber)).toList();
    }

    private boolean isSeatRestrictedByPerSeatsNumber(SeatDto seatDto, int restrictionValue) {
        if (restrictionValue < 2) {
            return false;
        }
        return seatDto.getAvailabilityStatus() == FREE && seatDto.getSeatNumber() % restrictionValue != 1;
    }

}

package com.globallogic.seatreservation.service;

import com.globallogic.seatreservation.domain.Restrictions;
import com.globallogic.seatreservation.domain.enumeration.AvailabilityStatus;
import com.globallogic.seatreservation.domain.enumeration.RestrictionType;
import com.globallogic.seatreservation.service.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestrictionHandlerTest {

    @Test
    void shouldRestrictSeatsWhenRestrictionIsPer3Seats() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final List<SeatDto> seatDtos = createSeats();
        final Restrictions restriction = creteRestriction(RestrictionType.PER_SEATS_NUMBER, 3);

        //when
        final List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        // Floor - 1
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIsPer2Seats() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final List<SeatDto> seatDtos = createSeats();
        final Restrictions restriction = creteRestriction(RestrictionType.PER_SEATS_NUMBER, 2);

        //when
        final List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        // Floor - 1
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIsPer1Seats() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final List<SeatDto> seatDtos = createSeats();
        final Restrictions restriction = creteRestriction(RestrictionType.PER_SEATS_NUMBER, 1);

        //when
        final List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        // Floor - 1
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIsPer0Seats() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final List<SeatDto> seatDtos = createSeats();
        final Restrictions restriction = creteRestriction(RestrictionType.PER_SEATS_NUMBER, 0);

        //when
        final List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        // Floor - 1
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIs50Percent() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final Restrictions restriction = creteRestriction(RestrictionType.PERCENTAGE, 50);
        final List<SeatDto> seatDtos = createSeats();

        //when
        List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        assertEquals(seatDtos.size(), seatsAfterRestriction.size());
        // Floor - 1
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIs100Percent() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final Restrictions restriction = creteRestriction(RestrictionType.PERCENTAGE, 100);
        final List<SeatDto> seatDtos = createSeats();

        //when
        List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        assertEquals(seatDtos.size(), seatsAfterRestriction.size());
        // Floor - 1
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIs0Percent() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final Restrictions restriction = creteRestriction(RestrictionType.PERCENTAGE, 0);
        final List<SeatDto> seatDtos = createSeats();

        //when
        List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        assertEquals(seatDtos.size(), seatsAfterRestriction.size());
        // Floor - 1
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    @Test
    void shouldRestrictSeatsWhenRestrictionIs30Percent() {
        //given
        final RestrictionHandler restrictionHandler = new RestrictionHandler();
        final Restrictions restriction = creteRestriction(RestrictionType.PERCENTAGE, 30);
        final List<SeatDto> seatDtos = createSeats();

        //when
        List<SeatDto> seatsAfterRestriction = restrictionHandler.getUpdatedSeatsAfterRestriction(restriction, seatDtos);

        //then
        assertNotNull(seatsAfterRestriction);
        assertEquals(seatDtos.size(), seatsAfterRestriction.size());
        // Floor - 1
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(0).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(1).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(2).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(3).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(4).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(5).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(6).getAvailabilityStatus());

        // Floor - 2
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(7).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(8).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(9).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(10).getAvailabilityStatus());

        // Floor - 3
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(11).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(12).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.RESTRICTED, seatsAfterRestriction.get(13).getAvailabilityStatus());
        assertEquals(AvailabilityStatus.FREE, seatsAfterRestriction.get(14).getAvailabilityStatus());
    }

    private Restrictions creteRestriction(RestrictionType restrictionType, Integer restrictionValue) {
        Restrictions restriction = new Restrictions();
        restriction.setRestrictionValue(restrictionValue);
        restriction.setRestrictionType(restrictionType);
        return restriction;
    }

    private List<SeatDto> createSeats() {
        LocationDto l1 = createLocationDto("1");
        LocationDto l2 = createLocationDto("2");

        BuildingDto b1 = createBuildingDto("b1 ", l1);
        BuildingDto b2 = createBuildingDto(" b2", l2);

        FloorDto f1 = createFloorDto(1, b1);
        FloorDto f2 = createFloorDto(2, b1);
        FloorDto f3 = createFloorDto(3, b2);

        RoomDto r1 = createRoomDto("r1", f1);
        RoomDto r2 = createRoomDto("r2", f2);
        RoomDto r3 = createRoomDto("r3", f3);

        List<SeatDto> room1Seats = IntStream.rangeClosed(1, 7)
            .mapToObj(seatNumber -> createSeatDto(seatNumber, r1))
            .toList();

        List<SeatDto> room2Seats = IntStream.rangeClosed(1, 4)
            .mapToObj(seatNumber -> createSeatDto(seatNumber, r2))
            .toList();

        List<SeatDto> room3Seats = IntStream.rangeClosed(1, 4)
            .mapToObj(seatNumber -> createSeatDto(seatNumber, r3))
            .toList();

        return Stream.of(room1Seats, room2Seats, room3Seats)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private SeatDto createSeatDto(Integer seatNumber, RoomDto room) {
        SeatDto seat = new SeatDto();
        seat.setRoom(room);
        seat.setSeatNumber(seatNumber);
        seat.setAvailabilityStatus(AvailabilityStatus.FREE);
        return seat;
    }

    private RoomDto createRoomDto(String name, FloorDto floor) {
        RoomDto room = new RoomDto();
        room.setName(name);
        room.setFloor(floor);
        return room;
    }

    private FloorDto createFloorDto(Integer number, BuildingDto buildingDto) {
        FloorDto floor = new FloorDto();
        floor.setId(Long.valueOf(number));
        floor.setNumber(number);
        floor.setBuilding(buildingDto);
        return floor;
    }

    private BuildingDto createBuildingDto(String name, LocationDto locationDto) {
        BuildingDto building = new BuildingDto();
        building.setName(name);
        building.setLocation(locationDto);
        return building;
    }

    private LocationDto createLocationDto(String name) {
        LocationDto location = new LocationDto();
        location.setName(name);
        return location;
    }

}

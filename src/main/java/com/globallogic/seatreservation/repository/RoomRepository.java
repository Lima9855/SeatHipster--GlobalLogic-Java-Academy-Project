package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findRoomsByFloorId(Pageable pageable, Long floorId);
}

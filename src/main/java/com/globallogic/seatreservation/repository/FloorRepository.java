package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Floor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    Page<Floor> findFloorsByBuildingId(Pageable pageable, Long buildingId);
}

package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Building;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Page<Building> findBuildingsByLocationId(Pageable pageable, Long locationId);
}

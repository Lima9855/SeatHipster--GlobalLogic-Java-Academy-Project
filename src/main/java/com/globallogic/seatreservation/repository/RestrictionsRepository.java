package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Restrictions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface RestrictionsRepository extends JpaRepository<Restrictions, Long> {
    List<Restrictions> findByToDateAfterAndFromDateBeforeOrderByFromDate(ZonedDateTime fromDate, ZonedDateTime toDate);
}

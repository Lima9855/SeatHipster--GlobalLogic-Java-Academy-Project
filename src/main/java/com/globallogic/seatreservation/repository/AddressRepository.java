package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Address;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}

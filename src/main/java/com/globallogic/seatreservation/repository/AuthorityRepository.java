package com.globallogic.seatreservation.repository;

import com.globallogic.seatreservation.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {}

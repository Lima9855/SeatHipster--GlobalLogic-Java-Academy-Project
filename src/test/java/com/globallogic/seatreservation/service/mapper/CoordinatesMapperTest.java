package com.globallogic.seatreservation.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordinatesMapperTest {

    private CoordinatesMapper coordinatesMapper;

    @BeforeEach
    public void setUp() {
        coordinatesMapper = new CoordinatesMapperImpl();
    }
}

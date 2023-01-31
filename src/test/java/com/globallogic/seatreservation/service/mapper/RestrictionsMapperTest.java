package com.globallogic.seatreservation.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestrictionsMapperTest {

    private RestrictionsMapper restrictionsMapper;

    @BeforeEach
    public void setUp() {
        restrictionsMapper = new RestrictionsMapperImpl();
    }
}

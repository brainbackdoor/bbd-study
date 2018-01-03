package com.educhoice.motherchoice.models.persistent.geolocation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AcademyAddressTest {

    private AcademyAddress address;

    @Before
    public void setUp() {
        this.address =
                AcademyAddress.builder()
                .zonecode("10120")
                .build();
}

    @Test
    public void 우편번호_뽑아오기() {
        assertEquals("10120", this.address.getZonecode());
    }

}
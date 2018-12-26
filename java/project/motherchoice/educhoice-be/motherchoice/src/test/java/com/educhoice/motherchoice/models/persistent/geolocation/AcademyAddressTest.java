package com.educhoice.motherchoice.models.persistent.geolocation;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void 언마샬링_테스트() throws Exception {
        String json = "{\"sido\" : \"경기도\", \"sigungu\" : \"김포시\", \"address\" : \"경기도 김포시 유현로 19\"}";

        AcademyAddress address = new ObjectMapper().readValue(json, AcademyAddress.class);

        assertEquals("경기도 김포시 유현로 19", address.getAddress());

    }

}
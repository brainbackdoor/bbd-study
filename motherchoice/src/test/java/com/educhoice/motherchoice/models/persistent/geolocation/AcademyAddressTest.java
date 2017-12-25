package com.educhoice.motherchoice.models.persistent.geolocation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AcademyAddressTest {

    private AcademyAddress address;

    @Before
    public void setUp() {
        this.address = new AcademyAddress("경기도","김포시","경기도 김포시 유현로 19", "경기도 김포시 유현로 19","경기도 김포시 풍무동 유현마을 신동아아파트",  "10120");
    }

    @Test
    public void 우편번호_뽑아오기() {
        assertEquals("10120", this.address.getZonecode());
    }

}
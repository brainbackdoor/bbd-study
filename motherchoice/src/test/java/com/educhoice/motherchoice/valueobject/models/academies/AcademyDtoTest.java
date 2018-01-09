package com.educhoice.motherchoice.valueobject.models.academies;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AcademyDtoTest {

    private static final Logger log = LoggerFactory.getLogger(AcademyDtoTest.class);
    private Academy academy;

    @Before
    public void setUp() {
        this.academy = Academy.builder()
                .academyId(1)
                .academyName("포비학원")
                .address(Arrays.asList(AcademyAddress.builder().address("경기도 수원시 영통구 이의동 243번지").roadAddress("경기도 수원시 포은대로 123").sido("경기도").sigungu("수원시 영통구").build()))
                .carAvailable(true)
                .introduction("당신도 포비처럼 코딩할 수 있다")
                .build();
    }

    @Test
    public void DTO객체_잘만들어지는지() {
        AcademyDto dto = AcademyDto.generateAcademyDto(this.academy);

        try {
            log.debug(new ObjectMapper().writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals("포비학원", dto.getAcademyName());
    }

}
package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.valueobject.models.academies.NewAcademyDto;
import com.educhoice.motherchoice.valueobject.models.accounts.CorporateAccountJoinDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserJoinRequestTest {
    private static final Logger log = LoggerFactory.getLogger(UserJoinRequestTest.class);

    private UserJoinRequest request;

    @Before
    public void setUp() {
        this.request = new UserJoinRequest();
        this.request.setRequestType(UserJoinRequest.JoinRequestType.ACADEMY);
        this.request.setAccountInfo(new CorporateAccountJoinDto("01012345678", "봄이네집", new NewAcademyDto()));
    }

    @Test
    public void 서브클래스_JSON_매핑() throws Exception {
        log.debug(new ObjectMapper().writeValueAsString(this.request));
    }

    @Test
    public void 학원요청_분기테스트() {
        assertThat(this.request.isCorporateAccountRequest(), is(true));
    }

    @Test
    public void JSON_서브클래스_인식테스트() throws Exception {
        String json = "{\"joinType\":1,\"account\":{\"requestType\" : \"corporate\",\"phoneNo\":\"01012345678\",\"originalName\":\"봄이네집\",\"academy\":{\"academyName\":null,\"ownerName\":null,\"academyPhoneNumber\":null,\"address\":null},\"loginId\":null,\"password\":null,\"terms\":false,\"privacy\":false,\"marketingInfo\":false},\"accessToken\":null}";
        UserJoinRequest testRequest = new ObjectMapper().readValue(json, UserJoinRequest.class);

        assertThat(testRequest.isCorporateAccountRequest(), is(true));
    }
}
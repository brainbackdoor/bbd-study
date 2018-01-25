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
}
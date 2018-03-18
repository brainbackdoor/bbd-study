package com.educhoice.motherchoice.valueobject.models.accounts;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParentsAccountJoinDtoTest {

    private static final String json = "{\"loginId\" : \"seulgi@smtown.com\", \"password\" : \"1234\", \"terms\" : \"true\", \"privacy\" : \"true\", \"marketingInfo\" : \"true\", \"memberAddress\" : \"서울시 강남구 역삼동\", \"nickname\" : \"봄이네집\"}";

    private ParentsAccountJoinDto dto;

    @Before
    public void setUp() throws Exception{
        this.dto = new ObjectMapper().readValue(json, ParentsAccountJoinDto.class);
    }

    @Test
    public void DTO_언마샬링_정보확인() {
        assertThat(this.dto.getLoginId(), is("seulgi@smtown.com"));
        assertThat(this.dto.getPassword(), is("1234"));
        assertThat(this.dto.isTerms(), is(true));
    }

    @Test
    public void DTO_계정생성() {
        Account account = this.dto.generateAccount();

        assertThat(account.getMemberAddress(), is("서울시 강남구 역삼동"));
        assertThat(account.getLoginId(), is("seulgi@smtown.com"));
    }

}
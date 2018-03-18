package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NaverSocialUserInfoTest {

    private String testJson = "{\n" +
            "    \"resultcode\":\"00\",\n" +
            "    \"message\": \"success\",\n" +
            "    \"response\":\n" +
            "    {\n" +
            "        \"email\":\"whei@seulgi.com\",\n" +
            "        \"nickname\":\"슬기웬디\",\n" +
            "        \"profile_image\":\"http://xxx.kakao.co.kr/.../aaa.jpg\",\n" +
            "        \"age\":\"20-29\",\n" +
            "        \"gender\":\"M\",\n" +
            "        \"id\":\"12341234\",\n" +
            "        \"name\":\"정휘준\",\n" +
            "        \"birthday\":\"12-17\"\n" +
            "    }\n" +
            "}";

    private NaverSocialUserInfo userInfo;

    @Before
    public void setUp() throws IOException {
        this.userInfo = new ObjectMapper().readValue(this.testJson, NaverSocialUserInfo.class);
    }

    @Test
    public void DTO_언마샬링() {
        assertEquals("슬기웬디", this.userInfo.getNickname());
        assertEquals(12341234L, this.userInfo.getUniqueId().longValue());
    }

}
package com.educhoice.motherchoice.configuration.security.service.social.userinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class KakaoSocialUserInfoTest {

    private KakaoSocialUserInfo userInfo;

    private String testJsonData = "{\n" +
            "    \"id\":123456789,\n" +
            "    \"kaccount_email\": \"xxxxxxx@xxxxx.com\",\n" +
            "    \"kaccount_email_verified\": true,\n" +
            "    \"properties\":\n" +
            "    {\n" +
            "        \"nickname\":\"홍길동\",\n" +
            "        \"thumbnail_image\":\"http://xxx.kakao.co.kr/.../aaa.jpg\",\n" +
            "        \"profile_image\":\"http://xxx.kakao.co.kr/.../bbb.jpg\",\n" +
            "        \"custom_field1\":\"23\",\n" +
            "        \"custom_field2\":\"여\"\n" +
            "    }\n" +
            "}";

    @Before
    public void setUp() throws IOException {
        this.userInfo = new ObjectMapper().readValue(testJsonData, KakaoSocialUserInfo.class);
    }

    @Test
    public void DTO객체_언마샬링테스트() {
        assertEquals("홍길동", this.userInfo.getNickname());
        assertTrue(this.userInfo.isEmailVerified());
    }

}
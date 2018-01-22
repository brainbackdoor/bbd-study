package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.KakaoSocialUserInfo;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthHttpRequestServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AuthHttpRequestServiceTest.class);

    @Autowired
    private AuthHttpRequestService service;

    private SocialSigninProviders providers = SocialSigninProviders.KAKAO;
    private SocialAuthinfoDto dto;

    @Before
    public void setUp() {
        this.dto = new SocialAuthinfoDto();
        this.dto.setAccessToken("jecBueyEzcx86U7yYER5adgcGWZvIWm_V2wb4Qo8BZUAAAFhHVcdpQ");
        this.dto.setProvider(this.providers);
        this.dto.setRefreshToken("pCKu-vhAHKbKwJuff9RiOa1fQUirM4-Y-pJdWgo8BZUAAAFhHKUOxg");
    }

    @Test
    public void 유저정보_가져오기_테스트() {
        BasicSocialUserInfo info = service.retrieveSocialUserInfo(this.dto);

        assertTrue(info instanceof KakaoSocialUserInfo);
        log.debug(info.toString());
        assertEquals("정휘준", info.getNickname());

    }


}
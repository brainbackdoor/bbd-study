package com.educhoice.motherchoice.utils;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthHttpRequestServiceTest {

    @Autowired
    private AuthHttpRequestService service;
//
//    @Test
//    public void GET요청_잘들어가는지() {
//        service.retrieveSocialUserInfo(SocialSigninProviders.TEST, "asdfasdflhjkl");
//    }

}
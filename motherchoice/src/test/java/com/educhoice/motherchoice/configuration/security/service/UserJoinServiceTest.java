package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserJoinServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserJoinServiceTest.class);

    private UserJoinRequest request;
    private SocialUserinfo userinfo;

    @Autowired
    private UserJoinService service;

    @Autowired
    private IntegratedUserQueryService queryService;

    @Before
    public void setUp() {
        this.request = UserJoinRequest.builder()
                .loginId("wheejuni@github.com")
                .password("1234")
                .joinType(UserJoinRequest.JoinRequestType.PARENTS)
                .marketingAllowed(true)
                .build();

        this.userinfo = SocialUserinfo.builder()
                .loginId("wheejuni@github.com")
                .profileUri("/profile/img/1")
                .socialId(1)
                .nickname("정휘준")
                .build();
    }

    @Test
    public void 소셜정보삽입_유저정보입력_잘되는지() {
        this.request.setAttributesFromSocialInfo(userinfo);
        log.debug("prepared user info is : {}" , this.request.toString());
        service.joinSocialUser(this.userinfo, this.request);
        assertNotNull(queryService.loadByEmail("wheejuni@github.com"));
        assertTrue(queryService.loadByEmail("wheejuni@github.com") instanceof Account);
    }



}
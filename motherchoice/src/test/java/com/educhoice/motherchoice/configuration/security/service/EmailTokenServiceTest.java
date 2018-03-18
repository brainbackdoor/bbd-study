package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTokenServiceTest {

    private Token token;

    @Autowired
    EmailTokenService emailTokenService;

    @Autowired
    RandomStringUtils randomStringUtils;

    @Before
    public void setUp() {
        Token setupToken = new Token();
        setupToken.setEmail("pobi@codesqd.kr");
        setupToken.setTokenValue(randomStringUtils.generateRandomString(20));
        this.token = setupToken;
    }

    @Test
    public void 키밸류값_잘들어가는지() {
        this.emailTokenService.putToken(this.token);
        assertTrue(emailTokenService.verifyEmail(this.token));
    }

    @Test
    public void 토큰인증_잘되는지() {
        this.emailTokenService.putToken(this.token);
        this.emailTokenService.verifyEmail(this.token);
        assertTrue(this.emailTokenService.isCertified("pobi@codesqd.kr"));
    }

    @Test
    public void 토큰생성_잘되는지() {
        assertThat(this.emailTokenService.generateToken("test@test.com").getTokenValue().length(), is(32));
    }

    @Test
    public void SAVE_TOKEN_AFTER_GENERATE() {
        emailTokenService.generateToken("test@test.com");

        assertThat(this.emailTokenService.isCertified("test@test.com"), is(false));
    }


}
package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenStorageServiceTest {

    private Token token;

    @Autowired
    TokenStorageService tokenStorageService;

    @Autowired
    RandomStringUtils randomStringUtils;

    @Before
    public void setUp() {
        Token setupToken = new Token();
        setupToken.setEmail("pobi@codesquad.kr");
        setupToken.setTokenValue(randomStringUtils.generateRandomString(20));
        this.token = setupToken;
    }

    @Test
    public void 키밸류값_잘들어가는지() {
        this.tokenStorageService.putToken(this.token);
        assertTrue(this.tokenStorageService.isCorrectToken("pobi@codesquad.kr", this.token.getTokenValue()));
    }


}
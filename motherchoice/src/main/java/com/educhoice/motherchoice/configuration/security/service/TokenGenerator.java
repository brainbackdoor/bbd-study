package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Email;
import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    private static final int TOKEN_KEY_LENGTH = 20;

    @Autowired
    private RandomStringUtils randomStringUtils;

    public Token generateToken(Email email) {
        return new Token(email.getAddress(),randomStringUtils.generateRandomString(TOKEN_KEY_LENGTH));
    }
}

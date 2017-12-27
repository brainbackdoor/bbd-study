package com.educhoice.motherchoice.models.nonpersistent.authorization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

    private String tokenValue;
    private String email;

    private boolean certified;

    public Token() {}

    public Token(String email, String tokenValue) {
        this.email = email;
        this.tokenValue = tokenValue;
        this.certified = false;
    }

    public boolean isCorrectToken(String tokenValue) {
        return tokenValue.equals(this.tokenValue);
    }
}

package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.google.common.base.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equal(tokenValue, token.tokenValue) &&
                Objects.equal(email, token.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tokenValue, email);
    }
}

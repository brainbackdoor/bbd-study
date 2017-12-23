package com.educhoice.motherchoice.models.nonpersistent.authorization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

    private String tokenValue;
    private String email;

    public boolean isMatchingEmail(String email) {
        return email.equals(this.email);
    }
}

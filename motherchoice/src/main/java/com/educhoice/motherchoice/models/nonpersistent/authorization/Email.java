package com.educhoice.motherchoice.models.nonpersistent.authorization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    private String address;

    public Email() {}

    public Email(String address) {
        this.address = address;
    }
}

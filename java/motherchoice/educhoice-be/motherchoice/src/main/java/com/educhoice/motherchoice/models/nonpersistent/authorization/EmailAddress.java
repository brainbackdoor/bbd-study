package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

@Getter
@Setter
public class EmailAddress {

    @JsonProperty(value = "user_email")
    @Email
    private String address;

    public EmailAddress() {}

    public EmailAddress(String address) {
        this.address = address;
    }
}

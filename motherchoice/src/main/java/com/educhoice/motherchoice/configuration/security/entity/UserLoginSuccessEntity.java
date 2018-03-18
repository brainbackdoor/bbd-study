package com.educhoice.motherchoice.configuration.security.entity;

import org.springframework.http.HttpStatus;

public class UserLoginSuccessEntity extends UserLoginResponseEntity {

    public String successMessage;

    public UserLoginSuccessEntity(String successMessage, HttpStatus status) {
        super(status);
        this.successMessage = successMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}

package com.educhoice.motherchoice.configuration.security.entity;

import org.springframework.http.HttpStatus;

public class UserLoginResponseEntity {

    private Throwable baseException;
    private int statusCode;

    public UserLoginResponseEntity(HttpStatus status) {
        this.statusCode = status.value();
    }

    public UserLoginResponseEntity(Throwable baseException, HttpStatus status) {
        this.baseException = baseException;
        this.statusCode = status.value();
    }

    public Throwable getBaseException() {
        return baseException;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

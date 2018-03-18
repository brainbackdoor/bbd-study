package com.educhoice.motherchoice.utils.exceptions.security;

import org.springframework.http.HttpStatus;

public class InvalidAccessTokenException extends SecurityException {

    public InvalidAccessTokenException(String msg) {
        super(msg);
    }

    public InvalidAccessTokenException(String msg, Throwable t) {
        super(msg, t);
        super.setStatus(HttpStatus.BAD_REQUEST);
    }
}

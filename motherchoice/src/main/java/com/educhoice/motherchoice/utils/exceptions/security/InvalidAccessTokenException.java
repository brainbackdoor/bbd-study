package com.educhoice.motherchoice.utils.exceptions.security;

public class InvalidAccessTokenException extends SecurityException {

    public InvalidAccessTokenException(String msg) {
        super(msg);
    }

    public InvalidAccessTokenException(String msg, Throwable t) {
        super(msg, t);
    }
}

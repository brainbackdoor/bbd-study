package com.educhoice.motherchoice.utils.exceptions.security;

import org.springframework.http.HttpStatus;

public class SecurityException extends RuntimeException {

    private final HttpStatus status = HttpStatus.FORBIDDEN;

    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(String msg, Throwable t) {
        super(msg, t);
    }

    public HttpStatus getStatus() {
        return status;
    }
}

package com.educhoice.motherchoice.utils.exceptions.security;

import org.springframework.http.HttpStatus;

public class SecurityException extends RuntimeException {

    private HttpStatus status = HttpStatus.FORBIDDEN;

    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public SecurityException(String msg, Throwable t) {
        super(msg, t);
    }

    public SecurityException(String msg, Throwable t, HttpStatus status) {
        super(msg, t);
        this.status = status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

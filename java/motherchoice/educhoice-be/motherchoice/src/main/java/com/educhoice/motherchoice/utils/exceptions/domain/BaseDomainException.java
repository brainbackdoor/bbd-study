package com.educhoice.motherchoice.utils.exceptions.domain;

import org.springframework.http.HttpStatus;

public class BaseDomainException extends RuntimeException {

    private HttpStatus defaultStatus = HttpStatus.BAD_REQUEST;

    public BaseDomainException(String message) {
        super(message);
    }

    public BaseDomainException(Throwable t, String message) {
        super(message, t);
    }

    public HttpStatus getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(HttpStatus defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public int getHttpStatusCode() {
        return this.defaultStatus.value();
    }
}

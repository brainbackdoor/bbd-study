package com.educhoice.motherchoice.utils.exceptions.security;

public class RequestParsingException extends RuntimeException {

    public RequestParsingException(String msg, Throwable t) {
        super(msg, t);
    }

    public RequestParsingException(String msg) {
        super(msg);
    }
}

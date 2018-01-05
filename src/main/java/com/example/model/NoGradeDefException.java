package com.example.model;

public class NoGradeDefException extends RuntimeException {

    public NoGradeDefException(String msg) {
        super(msg);
    }

    public NoGradeDefException(String msg, Throwable t) {
        super(msg, t);
    }
}

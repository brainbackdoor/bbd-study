package com.educhoice.motherchoice.utils.exceptions.domain;

public class NoGradeDefException extends BaseDomainException {

    public NoGradeDefException(String msg) {
        super(msg);
    }

    public NoGradeDefException(String msg, Throwable t) {
        super(t, msg);
    }
}

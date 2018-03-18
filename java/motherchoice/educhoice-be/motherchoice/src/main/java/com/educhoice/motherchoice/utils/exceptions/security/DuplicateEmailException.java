package com.educhoice.motherchoice.utils.exceptions.security;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String msg) {
        super(msg);
    }

    public DuplicateEmailException(String msg, Throwable t) {
        super(msg, t);
    }
}

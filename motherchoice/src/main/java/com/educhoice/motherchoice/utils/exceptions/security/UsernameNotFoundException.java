package com.educhoice.motherchoice.utils.exceptions.security;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends SecurityException {

    public UsernameNotFoundException(String msg) {
        super(msg);
    }

    public UsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}

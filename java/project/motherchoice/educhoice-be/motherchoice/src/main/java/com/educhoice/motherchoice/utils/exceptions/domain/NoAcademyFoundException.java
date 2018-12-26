package com.educhoice.motherchoice.utils.exceptions.domain;

import org.springframework.http.HttpStatus;

public class NoAcademyFoundException extends BaseDomainException {

    public NoAcademyFoundException(String msg) {
        super(msg);
        super.setDefaultStatus(HttpStatus.NOT_FOUND);
    }
}

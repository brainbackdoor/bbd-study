package com.educhoice.motherchoice.utils.exceptions.domain;

public class InternalProcessingException extends BaseDomainException {

    public InternalProcessingException(String message) {
        super(message);
    }

    public InternalProcessingException(Throwable t, String message) {
        super(t, message);
    }
}

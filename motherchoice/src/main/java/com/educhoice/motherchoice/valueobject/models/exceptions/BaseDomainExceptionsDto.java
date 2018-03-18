package com.educhoice.motherchoice.valueobject.models.exceptions;

import com.educhoice.motherchoice.utils.exceptions.domain.BaseDomainException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.HttpStatus;

public class BaseDomainExceptionsDto {

    @JsonProperty(value = "code")
    private int status;

    @JsonProperty(value = "message")
    private String message;

    public BaseDomainExceptionsDto(BaseDomainException e) {
        this.message = e.getMessage();
        this.status = e.getDefaultStatus().value();
    }
}

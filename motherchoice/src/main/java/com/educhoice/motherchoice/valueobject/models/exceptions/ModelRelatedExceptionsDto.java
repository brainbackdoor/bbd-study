package com.educhoice.motherchoice.valueobject.models.exceptions;

import com.educhoice.motherchoice.utils.exceptions.entity.NoAcademyIdException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.HttpStatus;

public class ModelRelatedExceptionsDto {

    @JsonProperty(value = "code")
    private final int status = HttpStatus.SC_NOT_FOUND;

    @JsonProperty(value = "message")
    private String message;

    public ModelRelatedExceptionsDto(NoAcademyIdException e) {
        this.message = e.getMessage();
    }


}

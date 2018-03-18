package com.educhoice.motherchoice.valueobject.security.exceptions;

import com.educhoice.motherchoice.utils.exceptions.security.SecurityException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class StandardSecurityExceptionResolverDto {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "message")
    private String errorMessage;

    public StandardSecurityExceptionResolverDto(SecurityException e) {
        this.code = e.getStatus().value();
        this.errorMessage = e.getMessage();
    }

    public StandardSecurityExceptionResolverDto(AuthenticationException e) {
        this.code = HttpStatus.UNAUTHORIZED.value();
        this.errorMessage = e.getMessage();
    }
}

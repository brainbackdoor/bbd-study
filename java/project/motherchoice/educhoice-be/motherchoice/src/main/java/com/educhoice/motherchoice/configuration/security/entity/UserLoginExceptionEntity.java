package com.educhoice.motherchoice.configuration.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserLoginExceptionEntity extends UserLoginResponseEntity{

    private static final String ERROR_LABEL = "NO USER MATCHES THE LOGIN REQUEST.";
    private String message;

    public UserLoginExceptionEntity(Exception exception, HttpStatus status) {
        super(exception, status);
        this.message = super.getBaseException().getMessage();
    }

    public static String getErrorLabel() {
        return ERROR_LABEL;
    }

    public String getMessage() {
        return message;
    }
}

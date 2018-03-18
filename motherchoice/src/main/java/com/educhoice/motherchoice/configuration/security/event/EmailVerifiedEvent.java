package com.educhoice.motherchoice.configuration.security.event;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailVerifiedEvent extends ApplicationEvent {

    private Token token;

    public EmailVerifiedEvent(Token token) {
        super(token);
        this.token = token;
    }
}

package com.educhoice.motherchoice.configuration.security.event.publisher;

import com.educhoice.motherchoice.configuration.security.event.EmailVerifiedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EmailVerifiedEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(EmailVerifiedEventPublisher.class);

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(EmailVerifiedEvent emailVerifiedEvent) {
        log.debug("now publishing event for EmailVerifiedEvent of : {}", emailVerifiedEvent.getToken().getEmail());
        publisher.publishEvent(emailVerifiedEvent);
    }
}

package com.educhoice.motherchoice.beans;

import com.educhoice.motherchoice.utils.exceptions.resolvers.SentryExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ExceptionResolvers {

    @Bean
    public SentryExceptionResolver sentryExceptionResolver() {
        return new SentryExceptionResolver();
    }
}

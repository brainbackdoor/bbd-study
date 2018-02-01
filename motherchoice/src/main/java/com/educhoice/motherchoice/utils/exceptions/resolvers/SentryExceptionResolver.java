package com.educhoice.motherchoice.utils.exceptions.resolvers;

import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SentryExceptionResolver implements HandlerExceptionResolver, Ordered{

    private static final Logger log = LoggerFactory.getLogger(SentryExceptionResolver.class);

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.error("sentry captured an exception : []" , e.getMessage());
        Sentry.init("https://9258b113675e430a9fd6d642164e12db:24ccba5f92b94b759cbe3744b851e5ee@sentry.io/281085");
        Sentry.capture(e);

        return null;
    }
}

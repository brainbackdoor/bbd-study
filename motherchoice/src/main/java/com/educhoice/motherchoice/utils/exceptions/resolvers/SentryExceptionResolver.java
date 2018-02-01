package com.educhoice.motherchoice.utils.exceptions.resolvers;

import io.sentry.Sentry;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SentryExceptionResolver implements HandlerExceptionResolver, Ordered{

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Sentry.init("https://9258b113675e430a9fd6d642164e12db:24ccba5f92b94b759cbe3744b851e5ee@sentry.io/281085");
        Sentry.capture(e);

        return null;
    }
}

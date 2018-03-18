package com.educhoice.motherchoice.utils.exceptions.resolvers.security;

import com.educhoice.motherchoice.utils.exceptions.security.SecurityException;
import com.educhoice.motherchoice.valueobject.security.exceptions.StandardSecurityExceptionResolverDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GlobalSecurityExceptionHandler implements HandlerExceptionResolver, Ordered{

    private static final Logger log = LoggerFactory.getLogger(GlobalSecurityExceptionHandler.class);

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        log.debug("something has caught on exception resolver.");

        if(e instanceof SecurityException) {
            SecurityException exception = (SecurityException)e;
            try {
                httpServletResponse.setStatus(exception.getStatus().value());
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new StandardSecurityExceptionResolverDto(exception)));
            } catch (IOException jsonException) {
                log.error(jsonException.getMessage());
            }
            return new ModelAndView();
        }
        log.debug("it wasn't an exception I've expected, so I did nothing.");
        return null;
    }
}

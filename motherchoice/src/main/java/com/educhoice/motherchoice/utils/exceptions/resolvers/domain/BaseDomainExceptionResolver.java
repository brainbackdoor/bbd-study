package com.educhoice.motherchoice.utils.exceptions.resolvers.domain;

import com.educhoice.motherchoice.utils.exceptions.domain.BaseDomainException;
import com.educhoice.motherchoice.valueobject.models.exceptions.BaseDomainExceptionsDto;
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

@Component
public class BaseDomainExceptionResolver implements HandlerExceptionResolver, Ordered{

    private static final Logger log = LoggerFactory.getLogger(BaseDomainExceptionResolver.class);

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object o, Exception originalException) {
        if (originalException instanceof BaseDomainException) {
            log.info("Model-related exception has caught.");
            BaseDomainException exception = (BaseDomainException) originalException;
            BaseDomainExceptionsDto dto = new BaseDomainExceptionsDto(exception);
            try {
                res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                res.setStatus(exception.getHttpStatusCode());
                res.getWriter().write(new ObjectMapper().writeValueAsString(dto));
            } catch(Exception e) {
                log.error(originalException.getMessage());
            }
            return new ModelAndView();
        }
        return null;
    }
}

package com.educhoice.motherchoice.utils.exceptions.resolvers.domain;

import com.educhoice.motherchoice.utils.exceptions.entity.NoAcademyIdException;
import com.educhoice.motherchoice.valueobject.models.exceptions.ModelRelatedExceptionsDto;
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
public class NoAcademyExceptionResolver implements HandlerExceptionResolver, Ordered{

    private static final Logger log = LoggerFactory.getLogger(NoAcademyExceptionResolver.class);

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object o, Exception originalException) {
        if (originalException instanceof NoAcademyIdException) {
            log.info("Model-related exception has caught.");
            NoAcademyIdException exception = (NoAcademyIdException)originalException;
            ModelRelatedExceptionsDto dto = new ModelRelatedExceptionsDto(exception);
            try {
                res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                res.getWriter().write(new ObjectMapper().writeValueAsString(dto));
            } catch(Exception e) {
                log.error(originalException.getMessage());
            }
            return new ModelAndView();
        }
        return null;
    }
}

package com.educhoice.motherchoice.configuration.security.service.filters;

import com.educhoice.motherchoice.utils.exceptions.security.SecurityException;
import com.educhoice.motherchoice.valueobject.security.exceptions.StandardSecurityExceptionResolverDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(req, res);
        } catch (SecurityException e) {
            res.setStatus(HttpStatus.SC_FORBIDDEN);
            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            res.getWriter().write(new ObjectMapper().writeValueAsString(new StandardSecurityExceptionResolverDto(e)));
        }
    }
}

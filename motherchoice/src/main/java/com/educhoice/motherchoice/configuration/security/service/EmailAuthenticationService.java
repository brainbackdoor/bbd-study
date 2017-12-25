package com.educhoice.motherchoice.configuration.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationService {

    @Autowired
    private TokenStorageService tokenStorageService;



}

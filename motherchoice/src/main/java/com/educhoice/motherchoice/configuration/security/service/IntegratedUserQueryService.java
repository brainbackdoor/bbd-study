package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.WonjangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntegratedUserQueryService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    WonjangRepository wonjangRepository;

    
}

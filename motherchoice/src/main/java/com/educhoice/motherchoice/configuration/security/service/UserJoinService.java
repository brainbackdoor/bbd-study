package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.utils.valueobject.security.UserJoinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserJoinService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CorporateAccountRepository corporateAccountRepository;

    @Autowired
    private TokenStorageService tokenStorageService;

    public void join(UserJoinRequest request) {

    }

}

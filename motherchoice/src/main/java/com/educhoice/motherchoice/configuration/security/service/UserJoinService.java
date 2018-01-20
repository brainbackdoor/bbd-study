package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.educhoice.motherchoice.service.AcademyService;
import com.educhoice.motherchoice.utils.exceptions.security.EmailNotCertifiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserJoinService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CorporateAccountRepository corporateAccountRepository;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private TokenStorageService tokenStorageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean isEmailCertified(String email) {
        return tokenStorageService.isCertified(email);
    }

}

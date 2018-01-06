package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
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
    private TokenStorageService tokenStorageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void join(UserJoinRequest request) {

    }

    public void joinSocialUser(SocialUserinfo userinfo, UserJoinRequest request) {
        if(request.isCorporateJoinRequest()) {
            CorporateAccount account = (CorporateAccount)request.generateAccount();
            account.setSocialId(userinfo.getSocialId());
            account.setProfileUri(userinfo.getProfileUri());
            corporateAccountRepository.save(account);
            return;
        }
        Account account = (Account)request.generateAccount();
        account.setSocialId(userinfo.getSocialId());
        account.setProfileUri(userinfo.getProfileUri());
        accountRepository.save(account);
    }

    private boolean isEmailCertified(String email) {
        return tokenStorageService.isCertified(email);
    }

}

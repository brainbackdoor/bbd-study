package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.educhoice.motherchoice.service.AcademyService;
import com.educhoice.motherchoice.utils.exceptions.entity.InvalidAcademyCreationException;
import com.educhoice.motherchoice.utils.exceptions.security.EmailNotCertifiedException;
import com.educhoice.motherchoice.valueobject.models.accounts.AccountJoinDto;
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

    public void joinAccount(UserJoinRequest request) {
        BasicAccount account = request.generateAccount();
        account.encryptPassword(passwordEncoder);

        if (account instanceof CorporateAccount) {
            CorporateAccount corporateAccount = (CorporateAccount)account;
            Academy academy = request.generateAcademyInfo().orElseThrow(() -> new InvalidAcademyCreationException("학원정보 생성에 실패하였습니다."));
            Academy savedAcademy = academyService.saveAcademy(academy);

            corporateAccount.setAcademy(savedAcademy);
            corporateAccountRepository.save(corporateAccount);
            return;
        }

        Account parentsAccount = (Account)account;
        accountRepository.save(parentsAccount);
    }

    private boolean isEmailCertified(String email) {
        return tokenStorageService.isCertified(email);
    }

}

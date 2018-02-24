package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.service.AcademyService;
import com.educhoice.motherchoice.utils.AuthHttpRequestService;
import com.educhoice.motherchoice.utils.exceptions.domain.InvalidAcademyCreationException;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserJoinService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthHttpRequestService requestService;

    @Autowired
    private CorporateAccountRepository corporateAccountRepository;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private TokenStorageService tokenStorageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void joinAccount(UserJoinRequest request) {
        if (request.isCorporateAccountRequest()) {
            corporateAccountRepository.save(this.generateCorporateAccount(request, passwordEncoder));
            return;
        }

        Account parentsAccount = (Account)request.generateAccount();
        parentsAccount.encryptPassword(passwordEncoder);
        accountRepository.save(parentsAccount);
    }

    public void joinRequestSocial(UserJoinRequest req) {
        if(req.isCorporateAccountRequest()) {
            this.corporateAccountRepository.save(generateSocialCorporateAccount(req));
            return;
        }
        Account account = (Account)req.generateAccount();
        setSocialInfos(account, req.getAuthinfoDto());
        account.encryptPassword(passwordEncoder);
        accountRepository.save((Account)setSocialInfos(account, req.getAuthinfoDto()));
    }

    private boolean isEmailCertified(String email) {
        return tokenStorageService.isCertified(email);
    }

    private CorporateAccount generateCorporateAccount(UserJoinRequest request, PasswordEncoder passwordEncoder) {
        Academy academy = this.academyService.saveAcademy(request.generateAcademyInfo().orElseThrow(() -> new InvalidAcademyCreationException("학원 정보 생성에 실패했습니다.")));
        CorporateAccount account = (CorporateAccount)request.generateAccount();

        account.encryptPassword(passwordEncoder);
        account.setAcademy(academy);
        return account;
    }

    private CorporateAccount generateSocialCorporateAccount(UserJoinRequest request) {
        return Arrays.asList(request).stream().map(req -> generateCorporateAccount(req, passwordEncoder))
                .peek(a -> a.setSocialInfos(retrieveInfos(request.getAuthinfoDto()), request.getAuthinfoDto()))
                .findFirst().get();
    }

    private BasicAccount setSocialInfos(BasicAccount account, SocialAuthinfoDto dto) {
        BasicSocialUserInfo info = retrieveInfos(dto);
        account.setSocialInfos(info, dto);
        return account;
    }

    private BasicSocialUserInfo retrieveInfos(SocialAuthinfoDto dto) {
        return requestService.retrieveSocialUserInfo(dto);
    }

}

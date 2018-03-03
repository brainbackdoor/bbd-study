package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Email;
import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.educhoice.motherchoice.utils.MailSendingUtils;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationService {

    @Autowired
    private TokenStorageService tokenStorageService;

    @Autowired
    private IntegratedUserQueryService integratedUserQueryService;

    @Autowired
    private MailSendingUtils mailSendingUtils;

    @Autowired
    private RandomStringUtils randomStringUtils;

    public void sendCertEmail(Email email) {
        Token token = new Token(email.getAddress(), randomStringUtils.generateRandomString(20));
        tokenStorageService.putToken(token);
        mailSendingUtils.sendMailForToken(token);
    }

    public void certifyEmail(Token token) {
        tokenStorageService.verifyEmail(token);
    }

    public boolean isGoodEmail(String email) {
        return tokenStorageService.isCertified(email) && !integratedUserQueryService.isExistingEmail(email);
    }

}

package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.EmailAddress;
import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.educhoice.motherchoice.utils.MailSendingUtils;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationService {

    @Autowired
    private EmailTokenService emailTokenService;

    @Autowired
    private IntegratedUserQueryService integratedUserQueryService;

    @Autowired
    private MailSendingUtils mailSendingUtils;

    @Autowired
    private RandomStringUtils randomStringUtils;

    public void sendCertEmail(EmailAddress email) {
        Token token = new Token(email.getAddress(), randomStringUtils.generateRandomString(20));
        emailTokenService.putToken(token);
        mailSendingUtils.sendMailForToken(token);
    }

    public void certifyEmail(Token token) {
        emailTokenService.verifyEmail(token);
    }

    public boolean isGoodEmail(String email) {
        return emailTokenService.isCertified(email) && !integratedUserQueryService.isExistingEmail(email);
    }

}

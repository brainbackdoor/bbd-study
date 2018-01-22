package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.configuration.security.service.AccountDetailsService;
import com.educhoice.motherchoice.configuration.security.service.IntegratedUserQueryService;
import com.educhoice.motherchoice.configuration.security.service.social.token.LoginAttemptToken;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.utils.AuthHttpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SocialLoginAuthenticationManager implements AuthenticationManager {


    @Autowired
    private AuthHttpRequestService requestService;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAttemptToken token = (LoginAttemptToken)authentication.getPrincipal();

        BasicSocialUserInfo userInfo = requestService.retrieveSocialUserInfo(token.getSocialDto());
        return new IntegratedUserSigninToken(getAccountFromUserinfo(userInfo));
    }

    private SecurityAccount getAccountFromUserinfo(BasicSocialUserInfo info) {
        return (SecurityAccount)accountDetailsService.loadUserBySocialId(info.getUniqueId());
    }
}

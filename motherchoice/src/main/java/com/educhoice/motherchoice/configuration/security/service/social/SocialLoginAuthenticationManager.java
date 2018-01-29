package com.educhoice.motherchoice.configuration.security.service.social;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.configuration.security.service.AccountDetailsService;
import com.educhoice.motherchoice.configuration.security.service.social.token.LoginAttemptToken;
import com.educhoice.motherchoice.configuration.security.service.social.userinfo.BasicSocialUserInfo;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.utils.AuthHttpRequestService;
import com.educhoice.motherchoice.valueobject.models.accounts.FormLoginRequestDto;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SocialLoginAuthenticationManager implements AuthenticationManager {

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.resource-ids}")
    private Set<String> resourceId;

    @Autowired
    private AuthHttpRequestService requestService;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAttemptToken token = (LoginAttemptToken)authentication;

        SocialAuthinfoDto socialDto = token.getSocialDto();
        FormLoginRequestDto formDto = null;

        if (socialDto == null) {
            formDto = token.getFormDto();
            SecurityAccount account = getAccountFromFormSignin(formDto);
            return new OAuth2Authentication(generateOAuthRequest(), new IntegratedUserSigninToken(account));
        }

        BasicSocialUserInfo userInfo = requestService.retrieveSocialUserInfo(socialDto);

        SecurityAccount account = getAccountFromUserinfo(userInfo, socialDto);

        return new OAuth2Authentication(new OAuth2Request(null, clientId, null, true, null, resourceId, null, null, null), new IntegratedUserSigninToken(account, socialDto.getProvider()));
    }

    private SecurityAccount getAccountFromUserinfo(BasicSocialUserInfo info, SocialAuthinfoDto dto) {
        return (SecurityAccount)accountDetailsService.loadUserBySocialId(info.getUniqueId(), dto.getProvider());
    }

    private SecurityAccount getAccountFromFormSignin(FormLoginRequestDto dto) {
        return accountDetailsService.loginByForm(dto);
    }

    private OAuth2Request generateOAuthRequest() {
        return new OAuth2Request(null, clientId, null, true, null, resourceId, null, null, null);
    }
}

package com.educhoice.motherchoice.configuration.security.service.social.token;

import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginAttemptToken extends UsernamePasswordAuthenticationToken {

    private SocialAuthinfoDto socialDto;

    public LoginAttemptToken(SocialAuthinfoDto dto) {
        super(dto, dto.getAccessToken(), null);
        this.socialDto = dto;
    }

    public SocialAuthinfoDto getSocialDto() {
        return socialDto;
    }
}

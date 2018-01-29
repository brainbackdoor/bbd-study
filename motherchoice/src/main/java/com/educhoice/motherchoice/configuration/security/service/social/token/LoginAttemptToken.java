package com.educhoice.motherchoice.configuration.security.service.social.token;

import com.educhoice.motherchoice.valueobject.models.accounts.FormLoginRequestDto;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginAttemptToken extends UsernamePasswordAuthenticationToken {

    private SocialAuthinfoDto socialDto;
    private FormLoginRequestDto formDto;

    public LoginAttemptToken(SocialAuthinfoDto dto) {
        super(dto, dto.getAccessToken(), null);
        this.socialDto = dto;
    }

    public LoginAttemptToken(FormLoginRequestDto dto) {
        super(dto, dto.getPassword(), null);
        this.formDto = dto;
    }

    public SocialAuthinfoDto getSocialDto() {
        return socialDto;
    }

    public FormLoginRequestDto getFormDto() {
        return formDto;
    }
}

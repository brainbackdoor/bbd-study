package com.educhoice.motherchoice.configuration.security.entity.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialUserinfo {

    private String username;
    private String email;
    private String profileUri;
    private int socialId;

}

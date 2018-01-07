package com.educhoice.motherchoice.configuration.security.entity.oauth;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SocialUserinfo {

    private String loginId;
    private String nickname;
    private String profileUri;
    private int socialId;

}

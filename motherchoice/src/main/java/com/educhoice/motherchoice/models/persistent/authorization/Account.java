package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.persistent.geolocation.MemberAddress;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.utils.converter.MemberAddrToStringConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account extends BasicAccount{

    private String nickname;
    private String memberAddress;

    private boolean marketingAllowed;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    public Account(String username, String password, String nickname, String memberAddress, String profileUri, boolean marketingAllowed, AccountRoles roles) {
        super(username, password, profileUri, roles);
        this.nickname = nickname;
        this.memberAddress = memberAddress;
        this.marketingAllowed = marketingAllowed;
    }

    public Account(String username, String password, String nickname, String memberAddress, String profileUri, boolean marketingAllowed, AccountRoles roles, SocialSigninProviders providers, Long socialId, String socialToken, String socialRefreshToken) {
        super(username, password, profileUri, roles, providers, socialId, socialToken, socialRefreshToken );
        this.nickname = nickname;
        this.memberAddress = memberAddress;
        this.marketingAllowed = marketingAllowed;
    }

    @Override
    public String toString() {
        return "Account{" +
                "nickname='" + nickname + '\'' +
                ", memberAddress=" + memberAddress +
                ", marketingAllowed=" + marketingAllowed +
                '}';
    }
}

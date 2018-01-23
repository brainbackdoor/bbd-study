package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BasicAccount {

    public enum AccountRoles {

        ADMIN,
        OAUTH_TEMPORARY_USER,
        PRE_INSPECTION_USER,
        UNPAID_USER,
        UNPAID_ACADEMY_USER,
        PAID_USER,
        INACTIVE_USER,
        QUIT_USER,
        FORCED_QUIT_USER;

        private String symbol;

        AccountRoles() {
            this.symbol = this.name();
        }

        public String getSymbol() {
            return this.symbol;
        }

        public static boolean isPaidTier(AccountRoles roles) {
            return roles == PAID_USER;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @NotNull
    @JsonIgnore
    private String loginId;

    @NotNull
    @JsonIgnore
    private String password;

    @JsonIgnore
    @Nullable
    private long socialId;

    @JsonIgnore
    @Nullable
    @Column(name = "OAUTH_ACCESS_TOKEN")
    private String socialtoken;

    @JsonIgnore
    @Nullable
    @Column(name = "OAUTH_REFRESH_TOKEN")
    private String socialRefreshToken;

    @Enumerated(value = EnumType.STRING)
    @Nullable
    @JsonIgnore
    private SocialSigninProviders socialProvider;

    @JsonIgnore
    private String profileUri;

    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private AccountRoles roles;

    public BasicAccount(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public BasicAccount(String loginId, String password, String profileUri, AccountRoles roles) {
        this.loginId = loginId;
        this.password = password;
        this.profileUri = profileUri;
        this.roles = roles;
    }

    public BasicAccount(String loginId, String password, String profileUri, AccountRoles roles, SocialSigninProviders socialProvider, Long socialId, String socialtoken, String socialRefreshToken) {
        this.loginId = loginId;
        this.password = password;
        this.profileUri = profileUri;
        this.roles = roles;
        this.socialProvider = socialProvider;
        this.socialId = socialId;
        this.socialtoken = socialtoken;
        this.socialRefreshToken = socialRefreshToken;
    }

    public boolean isSameEmail(String email) {
        return email.equals(this.loginId);
    }

    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }

    public void encryptPassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.password);
    }

}

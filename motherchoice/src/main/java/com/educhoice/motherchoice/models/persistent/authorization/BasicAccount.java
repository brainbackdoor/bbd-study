package com.educhoice.motherchoice.models.persistent.authorization;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    private int socialId;

    private String profileUri;

    @NotNull
    @Enumerated(value = EnumType.STRING)
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

    public boolean isSameEmail(String email) {
        return email.equals(this.loginId);
    }

    public void encryptPassword(PasswordEncoder encoder) {
        encoder.encode(this.password);
    }

}

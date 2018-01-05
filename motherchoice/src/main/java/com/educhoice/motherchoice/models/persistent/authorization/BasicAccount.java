package com.educhoice.motherchoice.models.persistent.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class BasicAccount {

    public enum AccountRoles {

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
    private String email;

    @NotNull
    private String password;

    private int socialId;

    private String profileUri;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private AccountRoles roles;

    public BasicAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public BasicAccount(String email, String password, String profileUri, AccountRoles roles) {
        this.email = email;
        this.password = password;
        this.profileUri = profileUri;
        this.roles = roles;
    }

    public boolean isSameEmail(String email) {
        return email.equals(this.email);
    }

}

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

        PRE_INSPECTION_USER,
        UNPAID_USER,
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

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private AccountRoles roles;

    public BasicAccount() {}

    public BasicAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean isSameEmail(String email) {
        return email.equals(this.email);
    }

}

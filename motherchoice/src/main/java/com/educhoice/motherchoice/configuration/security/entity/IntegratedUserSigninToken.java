package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class IntegratedUserSigninToken extends UsernamePasswordAuthenticationToken {

    private SecurityAccount account;


    public IntegratedUserSigninToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public IntegratedUserSigninToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public IntegratedUserSigninToken(SecurityAccount account) {
        super(account, null, account.getAuthorities());
        this.account = account;
    }



    public SecurityAccount getAccount() {
        return account;
    }

}

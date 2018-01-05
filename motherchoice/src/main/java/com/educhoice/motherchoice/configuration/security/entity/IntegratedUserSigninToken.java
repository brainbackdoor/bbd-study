package com.educhoice.motherchoice.configuration.security.entity;

import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Security;
import java.util.Arrays;
import java.util.Collection;

public class IntegratedUserSigninToken extends UsernamePasswordAuthenticationToken {

    private SecurityAccount account;
    private SocialUserinfo userinfo;

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

    public IntegratedUserSigninToken(SocialUserinfo userinfo) {
        super(userinfo, null, Arrays.asList(new SimpleGrantedAuthority(BasicAccount.AccountRoles.OAUTH_TEMPORARY_USER.getSymbol())));
        this.userinfo = userinfo;
    }

    public SecurityAccount getAccount() {
        return account;
    }

    public SocialUserinfo getUserinfo() {
        return userinfo;
    }
}

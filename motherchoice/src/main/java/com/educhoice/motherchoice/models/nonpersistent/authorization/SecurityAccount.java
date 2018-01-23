package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.educhoice.motherchoice.configuration.security.entity.oauth.SocialUserinfo;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityAccount extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private BasicAccount basicAccount;
    private SocialUserinfo userinfo;

    public SecurityAccount(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityAccount(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public SecurityAccount(BasicAccount account) {
        super(account.getLoginId(), account.getPassword(), true, true, true, true, makeAuthorities(Arrays.asList(account.getRoles())));
        this.basicAccount = account;
    }

    public SecurityAccount(SocialUserinfo userinfo) {
        super(userinfo.getLoginId(), "1111", makeAuthorities(Arrays.asList(BasicAccount.AccountRoles.OAUTH_TEMPORARY_USER)));
        this.userinfo = userinfo;
    }

    public BasicAccount getBasicAccount() {
        return basicAccount;
    }

    public SocialUserinfo getUserinfo() {
        return userinfo;
    }

    private static Collection<? extends GrantedAuthority> makeAuthorities(List<BasicAccount.AccountRoles> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(ROLE_PREFIX + r.getSymbol())).collect(Collectors.toList());
    }


}

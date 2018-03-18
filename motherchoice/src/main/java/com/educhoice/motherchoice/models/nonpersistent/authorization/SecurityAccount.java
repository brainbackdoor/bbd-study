package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityAccount extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private BasicAccount basicAccount;

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

    public BasicAccount getBasicAccount() {
        return basicAccount;
    }

    public boolean isPasswordCorrect(PasswordEncoder encoder, String password) {
        return encoder.matches(password, this.basicAccount.getPassword());
    }


    private static Collection<? extends GrantedAuthority> makeAuthorities(List<BasicAccount.AccountRoles> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(ROLE_PREFIX + r.getSymbol())).collect(Collectors.toList());
    }


}

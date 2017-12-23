package com.educhoice.motherchoice.models.nonpersistent.authorization;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.Wonjang;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Security;
import java.util.Collection;

public class SecurityAccount extends User {

    public SecurityAccount(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityAccount(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public SecurityAccount(Account account) {
        super(account.getEmail(), account.getPassword(), true, true, true, true, null);
    }

    public SecurityAccount(Wonjang wonjang) {
        super(wonjang.getEmail(), wonjang.getPassword(), true, true, true, true, null);
    }
}

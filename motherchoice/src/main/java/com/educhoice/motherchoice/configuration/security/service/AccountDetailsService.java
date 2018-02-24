package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.utils.exceptions.security.SecurityException;
import com.educhoice.motherchoice.valueobject.models.accounts.FormLoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {

	@Autowired
	IntegratedUserQueryService integratedUserQueryService;

	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return new SecurityAccount(integratedUserQueryService.loadByEmail(s));
	}

    public UserDetails loadUserBySocialId(long socialId, SocialSigninProviders providers) {
	    return new SecurityAccount(integratedUserQueryService.loadBySocialId(socialId, providers));
    }

    public SecurityAccount loadByFormLoginRequest(FormLoginRequestDto dto) {
        BasicAccount account = integratedUserQueryService.loadByEmail(dto.getLoginId());

        return new SecurityAccount(account);
    }

}

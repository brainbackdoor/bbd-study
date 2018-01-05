package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.SocialUserinfo;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {

	@Autowired
	IntegratedUserQueryService integratedUserQueryService;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return new SecurityAccount(integratedUserQueryService.loadByEmail(s));
	}

	public void saveSocialUser(SocialUserinfo userinfo) {

    }
}

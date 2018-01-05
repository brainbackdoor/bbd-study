package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntegratedUserQueryService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
    CorporateAccountRepository corporateAccountRepository;

	public BasicAccount loadByEmail(String email) throws UsernameNotFoundException{

		Optional<Account> account = accountRepository.findByEmail(email);
		return account.isPresent() ? account.get() : corporateAccountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
	}

	public boolean isExistingEmail(String email) {
		return accountRepository.findByEmail(email).isPresent() || corporateAccountRepository.findByEmail(email).isPresent();
	}
}

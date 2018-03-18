package com.educhoice.motherchoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.utils.exceptions.entity.NoAccountIdException;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account getAccountId(long accountId) {
		return accountRepository.findByAccountId(accountId).orElseThrow(() -> new NoAccountIdException("ID에 해당하는 계정이 없습니다."));
	}
}

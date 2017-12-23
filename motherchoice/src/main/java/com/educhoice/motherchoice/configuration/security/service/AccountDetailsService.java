package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.Wonjang;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.WonjangRepository;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.security.auth.login.AccountNotFoundException;

public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WonjangRepository wonjangRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SecurityAccount account = null;



       return account;
    }
}

package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.Basic;
import java.util.Optional;

@Component
public class IntegratedUserQueryService {

    private static final Logger log = LoggerFactory.getLogger(IntegratedUserQueryService.class);

	@Autowired
	AccountRepository accountRepository;

	@Autowired
    CorporateAccountRepository corporateAccountRepository;

	public BasicAccount loadByEmail(String email) throws UsernameNotFoundException {

		Optional<Account> account = accountRepository.findByLoginId(email);
		return account.isPresent() ? account.get() : corporateAccountRepository.findByLoginId(email).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
	}

	@Deprecated
    public BasicAccount loadBySocialId(long socialId) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findBySocialId(socialId);
        if(account.isPresent()) {
            log.debug("parents account is present!");
        }
        return account.isPresent() ? account.get() : corporateAccountRepository.findBySocialId(socialId).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
    }

	public BasicAccount loadBySocialId(long socialId, SocialSigninProviders providers) throws UsernameNotFoundException {
	    Optional<Account> account = accountRepository.findBySocialIdAndSocialProvider(socialId, providers);
	    return account.isPresent() ? account.get() : corporateAccountRepository.findBySocialIdAndSocialProvider(socialId, providers).orElseThrow(() -> new UsernameNotFoundException("user not found!"));
    }

	public boolean isExistingEmail(String email) {
		return accountRepository.findByLoginId(email).isPresent() || corporateAccountRepository.findByLoginId(email).isPresent();
	}

	public BasicAccount saveAccount(BasicAccount account) {
	    if(isCorporateAccount(account)) {
	        return corporateAccountRepository.save((CorporateAccount) account);
        }
        return accountRepository.save((Account)account);
    }

    private boolean isCorporateAccount(BasicAccount account) {
	    return account instanceof CorporateAccount;
    }
}

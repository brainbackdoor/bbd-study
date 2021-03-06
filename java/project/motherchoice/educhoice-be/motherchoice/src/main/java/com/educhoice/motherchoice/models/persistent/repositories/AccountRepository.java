package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long>{

    Optional<Account> findByLoginId(String loginId);
    Optional<Account> findBySocialId(long id);
    Optional<Account> findBySocialIdAndSocialProvider(long id, SocialSigninProviders provider);
    Optional<Account> findByNicknameContaining(String nickname);
    Optional<Account> findByAccountId(long accountId);
}

package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CorporateAccountRepository extends CrudRepository<CorporateAccount, Long> {

    Optional<CorporateAccount> findByLoginId(String email);
    Optional<CorporateAccount> findBySocialId(long id);
    Optional<CorporateAccount> findBySocialIdAndSocialProvider(long socialId, SocialSigninProviders provider);

}

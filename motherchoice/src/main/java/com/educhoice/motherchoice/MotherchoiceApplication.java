package com.educhoice.motherchoice;

import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.Course;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AcademyRepository;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.educhoice.motherchoice.models.persistent.repositories")
@EnableJpaAuditing
public class MotherchoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotherchoiceApplication.class, args);
	}

	@Bean
    public CommandLineRunner commandLineRunner(CorporateAccountRepository repository, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
            CorporateAccount account = new CorporateAccount("wheejuni", "1234", BasicAccount.AccountRoles.UNPAID_USER);
            account.encryptPassword(passwordEncoder);


            Account parentAccount = new Account("정휘준", "1234", "봄이네집", null, null, true, BasicAccount.AccountRoles.UNPAID_USER, SocialSigninProviders.KAKAO, 705692990L, "nL-jMA6reSagOq23xuheawJRNbF6qgMCAyehZwo8BRIAAAFhIbvBxw", null);

            accountRepository.save(parentAccount);
            repository.save(account);
        };
    }

}

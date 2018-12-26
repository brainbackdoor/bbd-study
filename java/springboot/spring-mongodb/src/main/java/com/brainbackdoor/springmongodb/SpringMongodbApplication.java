package com.brainbackdoor.springmongodb;

import com.brainbackdoor.springmongodb.account.Account;
import com.brainbackdoor.springmongodb.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringMongodbApplication {

//    @Autowired
//    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Account account = new Account();
            account.setUsername("brainbackdoor");
            account.setEmail("brainbackdoor@gmail.com");

//            mongoTemplate.save(account);
            accountRepository.insert(account);
            System.out.println("finished");
        };
    }
}


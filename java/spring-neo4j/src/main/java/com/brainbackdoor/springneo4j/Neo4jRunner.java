package com.brainbackdoor.springneo4j;

import com.brainbackdoor.springneo4j.account.Account;
import com.brainbackdoor.springneo4j.account.AccountRepository;
import com.brainbackdoor.springneo4j.account.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {
//    @Autowired
//    SessionFactory sessionFactory;
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Account account = new Account();
//        account.setUsername("bbd");
//        account.setEmail("brainbackdoor@gmail.com");
//
//        Role role = new Role();
//        role.setName("admin");
//        account.getRoles().add(role);
//
//        Session session = sessionFactory.openSession();
//
//        session.save(account);
//        sessionFactory.close();
//
//        System.out.println("finished");
//    }

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("bbd");
        account.setEmail("brainbackdoor@gmail.com");

        Role role = new Role();
        role.setName("user");

        account.getRoles().add(role);

        accountRepository.save(account);

        System.out.println("finished");
    }
}

package com.brainbackdoor.playgroundjpa01;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Component
public class JpaRunner implements ApplicationRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("bbd");
        account.setPassword("jpa");

        Study study = new Study();
        study.setName("Spring Data JPA");

        account.getStudies().add(study);
        study.setOwner(account);

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

    }
}

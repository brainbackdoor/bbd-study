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
//        Account account = new Account();
//        account.setUsername("bbd");
//        account.setPassword("jpa");
//
//        Study study = new Study();
//        study.setName("Spring Data JPA");
//
//        account.getStudies().add(study);
//        study.setOwner(account);
//
//        Session session = entityManager.unwrap(Session.class);
//        session.save(account);// persistent (그전까진 Transient)
//        session.save(study);
//
//        Account bbd = session.load(Account.class, account.getId());
//
//        // DB에 select query가 안나감
//        // print out이 먼저 생긴 후 insert query가 나감
//        bbd.setUsername("bbd2");
//        // 알아서 update :)
//        // dirty checking(객체의 변경사항을 감지)
//        // write behind(객체의 상태 변화를 최대한 필요한 시점에 적용)
////        bbd.setUsername("bbd"); update 안함
//        System.out.println(bbd.getUsername());
//        //transaction이 끝나면 detached 상태로 변함


        Post post = new Post();
        post.setTitle("Spring Data");

        Comment comment = new Comment();
        comment.setComment("우왕");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment.setComment("흙");
        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        session.save(post);
    }
}

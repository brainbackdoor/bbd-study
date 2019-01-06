package com.brainbackdoor.playgroundjpa01;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

//
//        Post post = new Post();
//        post.setTitle("Spring Data");
//
//        Comment comment = new Comment();
//        comment.setComment("우왕");
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//        comment.setComment("흙");
//        post.addComment(comment1);

//        Session session = entityManager.unwrap(Session.class);
////        session.save(post);
//
//        Post post1 = session.get(Post.class, 1L);
//        System.out.println(post1.getTitle());
////        post1.getComments().stream().forEach(v-> System.out.println(v.getComment()));
//        post1.getComments().forEach(v-> System.out.println(v.getComment()));

//        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
//        List<Post> posts = query.getResultList();
//        posts.forEach(System.out::println);

        // Type-Safe한 방식
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Post> query = builder.createQuery(Post.class);
//        Root<Post> root = query.from(Post.class);
//        query.select(root);
//        List<Post> posts = entityManager.createQuery(query).getResultList();
//        posts.forEach(System.out::println);

        //Native Query
        List<Post> posts = entityManager.createNativeQuery("Select * FROM Post", Post.class)
                .getResultList();
        posts.forEach(System.out::println);
    }
}

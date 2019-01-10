package com.example.demo;

import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

//    @Test
//    public void crud() {
//        postRepository.findMyPost();
//        Post post = new Post();
//        post.setTitle("hibernate");
//
//        postRepository.save(post);
//        postRepository.findMyPost();
//        postRepository.delete(post);
//        postRepository.flush();
//    }

//    @Test
//    public void crud() {
//        Post post = new Post();
//        post.setTitle("hibernate");
//
//        assertThat(postRepository.contains(post)).isFalse();
//
//        postRepository.save(post);
//
//        assertThat(postRepository.contains(post)).isTrue();
//
//        postRepository.delete(post);
//        postRepository.flush();
//    }

//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Test
//    public void event() {
//        Post post = new Post();
//        post.setTitle("event");
//        PostPusblishedEvent event = new PostPusblishedEvent(post);
//
//        applicationContext.publishEvent(event);
//    }

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("hibernate");

//        assertThat(postRepository.contains(post)).isFalse();
//
//        postRepository.save(post.publish());
//
//        assertThat(postRepository.contains(post)).isTrue();
//
//        postRepository.delete(post);
//        postRepository.flush();


        postRepository.save(post.publish());

        Predicate predicate = QPost.post.title.containsIgnoreCase("Hi");
        Optional<Post> one = postRepository.findOne(predicate);
        assertThat(one).isNotEmpty();
    }
}
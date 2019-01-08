package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
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

   @Test
   public void crud() {
       Post post = new Post();
       post.setTitle("hibernate");

       assertThat(postRepository.contains(post)).isFalse();

       postRepository.save(post);

       assertThat(postRepository.contains(post)).isTrue();

       postRepository.delete(post);
       postRepository.flush();
   }

}
package com.brainbackdoor.playgroundjpa01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @Rollback(false)
    public void crudRepository() {
        //Given
        Post post = new Post();
        post.setTitle("Hello spring boot");
        assertThat(post.getId()).isNull();

        //When
        Post newPost = postRepository.save(post);

        //Then
        assertThat(newPost.getId()).isNotNull();

        //When
        List<Post> posts = postRepository.findAll();

        //Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        //When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));

        //then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        //When
        page = postRepository.findByTitleContains("spring", PageRequest.of(0, 10));

        //then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);
    }
}
package com.brainbackdoor.playgroundjpa01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository repository;

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setComment("Hello Comment");

        repository.save(comment);

        List<Comment> all = repository.findAll();
        assertThat(all.size()).isEqualTo(1);

        long count = repository.count();
        assertThat(count).isEqualTo(1);

        Optional<Comment> byId = repository.findById(100L);
        assertThat(byId).isEmpty();

        Comment comment1 = byId.orElseThrow(IllegalArgumentException::new);
    }
}
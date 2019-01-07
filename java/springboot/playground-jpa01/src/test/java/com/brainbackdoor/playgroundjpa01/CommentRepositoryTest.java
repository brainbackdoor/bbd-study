package com.brainbackdoor.playgroundjpa01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    @Test
    public void crud2() {
        createComment(100, "spring data jpa");

        List<Comment> comments = repository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("Spring", 10);
        assertThat(comments.size()).isEqualTo(1);

    }

    private void createComment(int likeCount, String comment) {
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setLikeCount(likeCount);
        repository.save(newComment);
    }

    @Test
    public void crud3() {
        createComment(100, "spring data jpa");
        createComment(55, "HIBERNATE SPRING");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));
//        Page<Comment> comments = repository.findByCommentContainsIgnoreCase("Spring", pageRequest);
//        assertThat(comments.getNumberOfElements()).isEqualTo(2);
//        assertThat(comments).first().hasFieldOrPropertyWithValue("likeCount", 100);
        try(Stream<Comment> comments = repository.findByCommentContainsIgnoreCase("Spring", pageRequest)) {
            Comment firstComment = comments.findFirst().get();
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }
    }

}
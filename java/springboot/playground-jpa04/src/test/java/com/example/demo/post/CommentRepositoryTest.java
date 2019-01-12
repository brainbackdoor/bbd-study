package com.example.demo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.post.CommentSpecs.isBest;
import static com.example.demo.post.CommentSpecs.isGood;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository comments;

    @Autowired
    PostRepository posts;

    @Test
    public void getComment() {
//        comments.getById(1L);
//
//        comments.findById(1L);
//
//        comments.findByPost_Id(1L);

        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = posts.save(post);

        Comment comment = new Comment();
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);

        comments.save(comment);
        comments.findByPost_Id(savedPost.getId(), CommentSummary.class).forEach(c -> {
            System.out.println("==============");
            System.out.println(c.getVotes());
        });
    }

    @Test
    public void specs() {
        Page<Comment> page = comments
                .findAll(isBest().and(isGood()),
                        PageRequest.of(0, 10));
    }

    @Test
    public void qbe() {
        Comment prove = new Comment();
        prove.setBest(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withIgnorePaths("up", "down");
        Example<Comment> example = Example.of(prove, exampleMatcher);

        comments.findAll(example);

    }
}
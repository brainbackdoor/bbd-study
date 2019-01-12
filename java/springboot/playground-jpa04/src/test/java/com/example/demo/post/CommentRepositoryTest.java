package com.example.demo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
package com.brainbackdoor.playgroundjpa01;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

//@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
//public interface CommentRepository {
//    Comment save(Comment comment);
//
//    List<Comment> findAll();
//}
public interface CommentRepository extends MyRepository<Comment, Long> {

//    @Query(value = "SELECT c FROM Comment As c", nativeQuery = true) // SQL
//    @Query(value = "SELECT c FROM Comment As c") // JPQL
    List<Comment> findByCommentContains(String keyword); // 이름을 보고 유추

    Page<Comment> findByLikeCountGreaterThanAndPost(int likeCount, Post post, Pageable pageable);

}

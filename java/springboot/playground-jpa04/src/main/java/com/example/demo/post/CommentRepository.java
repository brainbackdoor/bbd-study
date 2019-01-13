package com.example.demo.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>,
        JpaSpecificationExecutor<Comment>,
        QueryByExampleExecutor<Comment> {

    @EntityGraph(value = "Comment.post")
    Optional<Comment> getById(Long id);

    @Transactional(readOnly = true)
    // readOnly를 사용할 경우, Flush 모드를 NEVER로 설정하여, Dirty Checking을 하지 않아도 된다.
    // flush는 보통 commit이 발생하거나, read를 할 떄 발생하는데
    // 데이터를 많이 읽을 경우 Dirty Checking을 하지 않는다면, 성능상에 큰 이점이 있다.
    <T> List<T> findByPost_Id(Long id, Class<T> type);
}

package com.example.demo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
/*
EntityManager가 postRepository에서 수행하는 트랜잭션 밖에 있을 경우 안먹힘
@SBT를 사용할 때 트랜잭션은 테스트가 아니라, PostRepository의 save()에만 적용이 되서,
해당 테스트 코드에서 사용하고 있는 EM이 전혀 그 객체를 모르게 됨

하지만 PostRepository의 트랜잭션 안에서는 알수 있음
@DJT에서는 트랜잭션이 테스트 단위이고, 따라서 그 안에서 실행하는 postRepository의 오퍼레이션과 EM이 모두 한 트랜잭션 안에 있고,
그래서 EM도 해당 객체에 대해 알 수 있게 된다.

즉, 트랜잭션의 범위에 따라 차이가 발생한 것!!
 */
public class PostRepositoryTest {
    @Autowired
    public PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post); // persist

        // savedPost.setTitle("bbd"); 리턴을 받아서 저장하는 습관을 가지자.

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(savedPost == post);

        /*
        Transient인지 Detached인지 어떻게 판단하는가?
        엔티티의 @Id 프로퍼티를 찾는다. 해당 프로퍼티가 null이면 Transient상태로 판단
        엔티티가 Persistable 인터페이스를 구현하고 있다면 isNew()메소드에 위임
         */

        Post postUpdate = new Post();
        postUpdate.setTitle("hibernate");
        postUpdate.setId(post.getId());
        Post updatedPost = postRepository.save(postUpdate); // merge -> update query가 발생

        postUpdate.setTitle("bbd"); // 따라서 이건 안먹힘, Persist 상태가 아니니까
//        updatedPost.setTitle("bbd"); // 요게 먹히는거

        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(entityManager.contains(postUpdate)).isFalse(); // 엔티티의 복사본을 만들고 그 복사본을 Persistent 상태로 변경하고 복사본을 반환
        assertThat(updatedPost == postUpdate);

        List<Post> posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith() {
        savePost();

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size()).isEqualTo(1);
    }

    private void savePost() {
        Post post = new Post();
        post.setTitle("Spring");
        postRepository.save(post);
    }

    @Test
    public void findByTitle() {
        savePost();

        List<Post> all = postRepository.findByTitle("Spring", JpaSort.unsafe("LENGTH(title)"));
//        List<Post> all = postRepository.findByTitle("Spring", Sort.by("title"));
        assertThat(all.size()).isEqualTo(1);
    }

}
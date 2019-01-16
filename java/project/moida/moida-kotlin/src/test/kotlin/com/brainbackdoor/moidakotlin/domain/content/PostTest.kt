package com.brainbackdoor.moidakotlin.domain.content

import com.brainbackdoor.moidakotlin.domain.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class PostTest {
    @Autowired lateinit var postRepository: PostRepository

    @Test
    fun `H2 연동 테스트`() {
        val feed = Feed(Member("bbd", "https://brainbackdoor.tistory.com"))
        val post = Post(
                feed.member,
                feed.syncFeed.entries[0].title, feed.syncFeed.entries[0].description.value)

        postRepository.save(post)

        val findAll = postRepository.findAll()
        assertThat(findAll[0].title).isEqualTo(post.title)
    }
}
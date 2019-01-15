package com.brainbackdoor.moidakotlin.domain.content

import com.brainbackdoor.moidakotlin.domain.member.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.ZoneId

class FeedTest {
    @Test
    fun `🚀 Let's get rss feed information of url`() {
        val member = Member("bbd","https://brainbackdoor.tistory.com")
        val feed = Feed(member)

        assertThat(feed.url).isEqualTo(member.blogLink + "/rss")
        assertThat(feed.syncFeed.title).isEqualTo("Why? What? How?")

//        println(feed.syncFeed.entries[0].title)
//        println(feed.syncFeed.entries[0].link)
    }
}
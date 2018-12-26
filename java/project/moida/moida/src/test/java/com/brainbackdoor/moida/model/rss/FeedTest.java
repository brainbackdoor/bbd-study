package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

class FeedTest {
    private String URL = "https://brainbackdoor.tistory.com/rss";

    @BeforeEach
    void setUp() throws IOException, FeedException {
        syndFeed = new SyndFeedInput().build(new XmlReader(new URL(URL)));
        member = Member.builder()
                .name("bbd")
                .blogLink("https://brainbackdoor.tistory.com")
                .build();
    }
    SyndFeed syndFeed;
    Member member;

    @Test
    void create()  {
        Feed feed = new Feed(member, syndFeed);
        assertThat(feed.getCreatedDate(), lessThan(LocalDateTime.now()));
    }
}
package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.rss.Feed;
import com.brainbackdoor.moida.model.rss.Rss;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    private String URL = "https://brainbackdoor.tistory.com/rss";

    @BeforeEach
    void setUp() throws IOException, FeedException {
        syndFeed = new SyndFeedInput().build(new XmlReader(new URL(URL)));
        member = Member.builder().name("bbd").blogLink("https://brainbackdoor.tistory.com").build();
    }

    SyndFeed syndFeed;
    Member member;

    @Test
    void createFeedback() {
        Feed feed = new Feed(member, syndFeed);
        Feedback feedback = new Feedback(feed, member,"댓글");

        assertThat(feedback.getContent(), is("댓글"));
        assertThat(feedback.getMember().getName(), is("bbd"));
    }
}
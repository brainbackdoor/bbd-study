package com.brainbackdoor.moida.model.rss;

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

class FeedTest {
    private String URL = "https://brainbackdoor.tistory.com/rss";

    @BeforeEach
    void setUp() throws IOException, FeedException {
        syndFeed = new SyndFeedInput().build(new XmlReader(new URL(URL)));
    }
    SyndFeed syndFeed;

    @Test
    void create() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("name", "bbd");
        map.put("blogLink", "https://brainbackdoor.tistory.com");
        Feed feed = new Feed(Rss.buildFeedItems(map), syndFeed);
        assertThat(feed.getMember().getName(), is("bbd"));
    }
}
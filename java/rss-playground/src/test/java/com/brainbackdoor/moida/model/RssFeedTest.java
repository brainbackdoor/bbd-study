package com.brainbackdoor.moida.model;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.feed.rss.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
class RssFeedTest {

    @Autowired
    private RssFeed rssFeed;

    Map map;

    @BeforeEach
    void setUp () {
        map = new HashMap();
        map.put("author", "bbd");
    }

    @Test
    void buildFeedItems() throws Exception {
        rssFeed.buildFeedItems(map, null, null);
    }

    @Test
    void mapping() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));
        System.out.println(mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).convertValue(map, Feed.class).getAuthor());
    }
    private static class Feed extends Item {
        String author;
        String title;
        String content;
        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
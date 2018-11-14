package com.brainbackdoor.moida.model;


import com.rometools.rome.feed.rss.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@RunWith(SpringRunner.class)
class RssFeedTest {

    @Autowired
    private RssFeed rssFeed;

    @BeforeEach
    void setUp () {
        article = Article.builder().author("bbd").build();
        map = new HashMap();
        map.put("article", article);
    }

    Map map;
    Article article;

    @Test
    void buildItems() throws Exception {
        Item item = (Item) rssFeed.buildFeedItems(map, null, null).get(0);
        assertThat(item.getAuthor(),is(article.getAuthor()));
    }

}
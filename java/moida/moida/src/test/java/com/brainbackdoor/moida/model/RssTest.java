package com.brainbackdoor.moida.model;


import com.brainbackdoor.moida.model.rss.Rss;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class RssTest {

    @BeforeEach
    void setUp () {
        member = Member.builder().name("bbd").build();
    }

    Member member;

    @Test
    void buildItems() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("name", "bbd");
        map.put("blogLink", "https://brainbackdoor.tistory.com");
        assertThat(Rss.buildFeedItems(map).getName(),is(member.getName()));
    }

    @Test
    @Disabled
    @DisplayName("🚀 Let's get rss feed information of url")
    void requestRss() throws MalformedURLException {
        // uri with /rss
        String rssUrl = "http://brainbackdoor.tistory.com/rss";
        URL feedUrl = new URL(rssUrl);
        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            System.out.println("RSS title: " + feed.getTitle());
            System.out.println("RSS author: " + feed.getAuthor());

            List entries = feed.getEntries();
            for (int i = 0; i < entries.size(); i++) {
                SyndEntry entry = (SyndEntry) entries.get(i);
                System.out.println("--- Entry " + i);
                System.out.println(entry.getTitle());
                System.out.println(entry.getAuthor());
                System.out.println(entry.getDescription().getValue());
                System.out.println(entry.getLink());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
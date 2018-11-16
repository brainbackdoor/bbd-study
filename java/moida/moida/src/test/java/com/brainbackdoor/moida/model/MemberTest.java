package com.brainbackdoor.moida.model;

import com.brainbackdoor.moida.model.rss.Rss;
import com.brainbackdoor.moida.service.RssService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
class MemberTest {

    @Autowired
    private RssService rssService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        member = Member.builder().name("bbd").blogLink("https://brainbackdoor.tistory.com").build();
    }

    Member member;

    @Test
    void getMemberFeed() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("name", "bbd");
        map.put("blogLink", "https://brainbackdoor.tistory.com");
        assertThat(rssService.request(Rss.buildFeedItems(map)).getMember().getName(), is("bbd"));
    }
}
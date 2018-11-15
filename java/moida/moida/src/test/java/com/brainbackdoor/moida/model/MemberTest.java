package com.brainbackdoor.moida.model;

import com.brainbackdoor.moida.model.rss.RssFeed;
import com.brainbackdoor.moida.service.RssService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
class MemberTest {

    @Autowired
    private RssService rssService;

    @Autowired
    private RssFeed rssFeed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        member = Member.builder().name("bbd").blogLink("https://brainbackdoor.tistory.com").build();
    }

    Member member;

    @Test
    void getMemberFeed() throws Exception {
        assertThat(rssService.request(rssFeed.buildFeedItems(member.get(), null, null)).getAuthor(), is("가그린민트"));
    }
}
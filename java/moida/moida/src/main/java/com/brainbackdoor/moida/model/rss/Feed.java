package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.history.FeedHistory;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.Getter;

import java.util.List;

@Getter
public class Feed {
    Member member;
    String title;
    String link;
    String description;
    String version;
    List<FeedHistory> feedHistories;

    public Feed(Member member, SyndFeed syndFeed) {
        this.member = member;
        this.title = syndFeed.getTitle();
        this.link = syndFeed.getLink();
        this.description = syndFeed.getDescription();
        // TODO: GET Feed Histroy
    }
}

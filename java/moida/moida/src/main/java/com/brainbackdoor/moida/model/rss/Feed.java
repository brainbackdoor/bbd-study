package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.history.FeedHistory;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
public class Feed {
    Member member;
    String title;
    String link;
    String description;
    LocalDateTime createdDate;
    String version;
    FeedHistory feedHistory;

    public Feed(Member member, SyndFeed syndFeed) {
        this.member = member;
        this.title = syndFeed.getTitle();
        this.link = syndFeed.getLink();
        this.description = syndFeed.getDescription();
        this.createdDate = syndFeed.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        feedHistory = new FeedHistory(this);
        //TODO: Add Feed History DAO
    }
}

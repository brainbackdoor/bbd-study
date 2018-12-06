package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.history.FeedHistory;
import com.rometools.rome.feed.synd.SyndContent;
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
    SyndContent description;
    LocalDateTime createdDate;
    String version;
    FeedHistory feedHistory;

    public Feed(Member member, SyndFeed syndFeed) {
        this.member = member;
        this.title = syndFeed.getTitle();
        this.title = syndFeed.getEntries().get(0).getTitle();
        this.link = syndFeed.getEntries().get(0).getLink();
        this.description = syndFeed.getEntries().get(0).getDescription();
        this.createdDate = syndFeed.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        feedHistory = new FeedHistory(this);
        //TODO: Add Feed History DAO
    }

    @Override
    public String toString() {
        return "Feed{" +
                "member=" + member +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description=" + description +
                ", createdDate=" + createdDate +
                ", version='" + version + '\'' +
                ", feedHistory=" + feedHistory +
                '}';
    }
}

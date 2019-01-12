package com.brainbackdoor.moida.model.rss;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.content.Feedback;
import com.brainbackdoor.moida.model.history.FeedHistory;
import com.brainbackdoor.moida.model.history.History;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Feed {
    Member member;
    String title;
    String link;
    SyndContent description;
    LocalDateTime createdDate;
    String version;
    History feedHistory;
    List<Feedback> feedbacks = new ArrayList<>();

    public Feed(Member member, SyndFeed syndFeed) {
        this.member = member;
        this.title = syndFeed.getTitle();
        this.title = syndFeed.getEntries().get(0).getTitle();
        this.link = syndFeed.getEntries().get(0).getLink();
        this.description = syndFeed.getEntries().get(0).getDescription();
        this.createdDate = syndFeed.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.feedHistory = new FeedHistory(this);
        //TODO: Add Feed History DAO
    }

    public void add(Feedback feedback) {
        feedbacks.add(feedback);
    }
}

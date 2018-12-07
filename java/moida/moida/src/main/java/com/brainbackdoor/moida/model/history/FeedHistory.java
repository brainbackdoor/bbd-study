package com.brainbackdoor.moida.model.history;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.rss.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedHistory extends History{
    Member member;
    String title;


    public FeedHistory(Feed feed) {
        this.member = feed.getMember();
        this.title = feed.getTitle();
        this.createdDate = LocalDateTime.now();
        //TODO: write contents
    }
}

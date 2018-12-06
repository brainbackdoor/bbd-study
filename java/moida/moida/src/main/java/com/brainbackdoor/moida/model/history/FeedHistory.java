package com.brainbackdoor.moida.model.history;

import com.brainbackdoor.moida.model.rss.Feed;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class FeedHistory extends History{
    Feed feed;

    public FeedHistory(Feed feed) {
        this.createdDate = LocalDateTime.now();
        this.contents = "...";
    }
}

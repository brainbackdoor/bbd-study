package com.brainbackdoor.moida.model.history;

import com.brainbackdoor.moida.model.rss.Feed;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class FeedHistory {
    LocalDateTime createdDate;
    Feed feed;
    String contents;

    public FeedHistory(Feed feed) {
        this.createdDate = LocalDateTime.now();
        this.feed = feed;
        this.contents = "...";
    }
}

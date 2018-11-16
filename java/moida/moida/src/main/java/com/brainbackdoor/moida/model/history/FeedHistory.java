package com.brainbackdoor.moida.model.history;

import com.brainbackdoor.moida.model.rss.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class FeedHistory {
    LocalDateTime createdDate;
    Feed feed;
    String contents;
}

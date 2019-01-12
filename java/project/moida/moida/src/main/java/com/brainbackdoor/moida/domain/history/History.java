package com.brainbackdoor.moida.model.history;

import com.brainbackdoor.moida.model.rss.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class History {
    LocalDateTime createdDate;
    String contents;
}

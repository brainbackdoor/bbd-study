package com.brainbackdoor.moida.model.history;

import com.brainbackdoor.moida.model.rss.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class CheckListHistory extends History {

    public CheckListHistory(Feed feed) {
        this.createdDate = LocalDateTime.now();
        this.contents = "...";
    }
}

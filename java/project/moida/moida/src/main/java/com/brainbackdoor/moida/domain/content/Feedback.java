package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.model.Member;
import com.brainbackdoor.moida.model.rss.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Feedback {
    private Member member;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Feedback(Member member, String content) {
        this.member = member;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }
    // TODO: DAO & Update logic
}

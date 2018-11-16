package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.model.Member;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Form {
    private List<Item> items;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Member author;
}

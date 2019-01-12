package com.brainbackdoor.moida.model.content;

import com.brainbackdoor.moida.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private List<Item> items;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Member author;
    //TODO: Get FORM from DB

    //TODO: PUT FORM
    public Form (List<Item> items, Member author) {
        this.items = items;
        this.author = author;
        this.createdDate = LocalDateTime.now();
    }
}

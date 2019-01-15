package com.brainbackdoor.moida.domain.content;

import com.brainbackdoor.moida.domain.member.Member;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Form {
    @Id
    @GeneratedValue
    private Long id;

    private List<Item> items;

    private Member author;
}

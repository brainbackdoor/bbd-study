package com.brainbackdoor.moida.domain.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private boolean confirm;
}

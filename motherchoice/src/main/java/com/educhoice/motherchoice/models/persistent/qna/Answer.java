package com.educhoice.motherchoice.models.persistent.qna;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    @OneToMany
    @JoinColumn(name = "answerid")
    private List<Reply> replies;

}

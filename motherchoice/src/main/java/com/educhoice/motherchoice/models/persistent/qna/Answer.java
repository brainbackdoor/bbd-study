package com.educhoice.motherchoice.models.persistent.qna;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerid")
    private List<Reply> replies;

    @ManyToOne
    @JsonIgnore
    private Question question;

}

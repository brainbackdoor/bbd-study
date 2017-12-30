package com.educhoice.motherchoice.models.persistent.qna;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Question extends BaseTimeEntity{

    @Id
    @GeneratedValue
    private long questionId;

    private String title;
    private String content;

    @OneToMany
    private List<Academy> academies;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers;
}

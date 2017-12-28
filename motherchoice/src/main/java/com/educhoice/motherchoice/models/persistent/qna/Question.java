package com.educhoice.motherchoice.models.persistent.qna;

import com.educhoice.motherchoice.models.persistent.Academy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue
    private long questionId;

    private String title;
    private String content;

    @OneToMany
    private List<Academy> academies;
}

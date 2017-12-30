package com.educhoice.motherchoice.models.persistent.qna;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseTimeEntity{

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

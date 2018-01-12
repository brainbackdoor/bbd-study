package com.educhoice.motherchoice.models.persistent.qna;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseTimeEntity{

    @Id
    @GeneratedValue
    private long questionId;

    private String title;
    private String content;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "academyId")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Academy> academies;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers;

    @ManyToOne
    private Account writer;

    public int getQuestionedAcademyCount() {
        return this.academies.size();
    }

    public int getAnswersCount() {
        return this.answers.size();
    }

    public int getRepliesCount() {
        return this.answers.stream().mapToInt(a -> a.getRepliesCount()).sum();
    }
}

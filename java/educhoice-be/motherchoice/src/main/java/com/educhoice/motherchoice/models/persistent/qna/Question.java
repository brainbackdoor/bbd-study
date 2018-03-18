package com.educhoice.motherchoice.models.persistent.qna;

import com.educhoice.motherchoice.models.domainevents.NewQuestionEvent;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseTimeEntity{

    @Transient
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Id
    @GeneratedValue
    private long questionId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "academyId")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Academy> academies;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers;

    @ManyToOne
    private Account writer;

    @DomainEvents
    NewQuestionEvent newQuestionEvent() {
        return new NewQuestionEvent(this);
    }

    @AfterDomainEventPublication
    public void onSuccessfulPublication() {
        log.info("new question just registered.");
    }

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

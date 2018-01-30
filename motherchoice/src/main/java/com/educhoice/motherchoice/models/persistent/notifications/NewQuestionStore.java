package com.educhoice.motherchoice.models.persistent.notifications;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Getter
@Setter
@NoArgsConstructor
public class NewQuestionStore {

    @Id
    private long corporateAccountId;

    private Map<Long, NewQuestion> questions = Maps.newHashMap();

    public boolean hasNewQuestion() {
        return questions.values().stream().anyMatch(nq -> !nq.isRead());
    }
}

package com.educhoice.motherchoice.models.persistent.notifications;

import com.educhoice.motherchoice.models.domainevents.NewQuestionEvent;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewQuestion {

    private long questionId;
    private boolean read;

    public NewQuestion(NewQuestionEvent event) {
        this.questionId = event.getQuestionId();
        this.read = false;
    }

    public void setRead() {
        this.read = true;
    }

}

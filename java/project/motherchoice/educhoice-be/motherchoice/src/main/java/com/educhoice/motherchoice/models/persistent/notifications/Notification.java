package com.educhoice.motherchoice.models.persistent.notifications;

import com.educhoice.motherchoice.models.domainevents.NewQuestionEvent;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    public enum NotificationTypes {
        NEW_QUESTION,
        NEW_ANSWER;
    }

    @Id
    private String userId;

    private String message;
    private String href;

    public Notification(NewQuestionEvent event) {
        this.userId = "1";
        this.message = "새로운 질문이 등록되었습니다.";
        this.href = "http://www.naver.com";
    }

}

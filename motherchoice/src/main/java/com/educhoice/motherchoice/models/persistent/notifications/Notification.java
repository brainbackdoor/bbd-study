package com.educhoice.motherchoice.models.persistent.notifications;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Notification {

    @Id
    private String userId;

    private String message;
    private String href;

}

package com.educhoice.motherchoice.models.persistent.notifications;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewQuestion {

    private long questionId;
    private boolean read;

}

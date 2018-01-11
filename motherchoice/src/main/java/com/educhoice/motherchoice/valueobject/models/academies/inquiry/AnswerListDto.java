package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@JsonRootName("answer")
public class AnswerDto {

    private String academyName;
    private String created;
    private int answerId;
    private boolean newFlag;

    //TODO Write model-based constructor.

}

package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("averageQuestion")
public class AverageQuestionDto {

    private int averageMonthlyQuesiton;
    private int averageWeeklyQuestion;
}

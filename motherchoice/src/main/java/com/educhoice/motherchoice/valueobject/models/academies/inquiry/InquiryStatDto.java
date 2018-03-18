package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import java.util.List;

public class InquiryStatDto {

    private int totalQuestion;
    private AverageQuestionDto averageQuestion;
    private int monthlyQuestion;
    private int weeklyQuestion;
    private ChartDataDto chartData;
    private List<QuestionListDto> questions;
}

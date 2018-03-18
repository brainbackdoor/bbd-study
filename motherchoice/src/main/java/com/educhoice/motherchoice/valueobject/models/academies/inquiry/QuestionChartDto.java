package com.educhoice.motherchoice.valueobject.models.academies.inquiry;

import com.educhoice.motherchoice.models.persistent.qna.MonthlyQuestionStatistics;
import com.educhoice.motherchoice.models.persistent.qna.WeeklyQuestionStatistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionChartDto {

        private MonthlyQuestionStatistics monthQuestions;
        private WeeklyQuestionStatistics weekQuestions;

        public int retrieveTotalQuestion() {
                return monthQuestions.getTotal();
        }

        public int retrieveMonthlyQuestion() {
                return retrieveTotalQuestion() / 6;
        }

        public int retrieveWeeklyQuestion() {
                return retrieveTotalQuestion() / 42;
        }

        public int retrieveThisMontlyQuestion() {
                return monthQuestions.getThisMonth();
        }

        public int retrieveThisWeeklyQuestion() {
                return weekQuestions.getThisWeek();
        }
}
package com.educhoice.motherchoice.models.persistent.qna;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="month_questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyQuestionStatistics {

        @Id
        @Column(name = "academies_academy_id")
        private Long academiesAcademyId;

        private int total;

        private int thisMonth;

        private int oneMonthsAgo;

        private int twoMonthsAgo;

        private int threeMonthsAgo;

        private int fourMonthsAgo;

        private int fiveMonthsAgo;
}
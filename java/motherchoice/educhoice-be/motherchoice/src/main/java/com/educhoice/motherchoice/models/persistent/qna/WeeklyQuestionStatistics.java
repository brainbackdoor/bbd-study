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
@Table(name="week_questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyQuestionStatistics {

        @Id
        @Column(name = "academies_academy_id")
        private Long academiesAcademyId;

        private int thisWeek;

        private int oneWeeksAgo;

        private int twoWeeksAgo;

        private int threeWeeksAgo;

        private int fourWeeksAgo;

        private int fiveWeeksAgo;
}
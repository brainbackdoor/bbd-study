package com.educhoice.motherchoice.models.persistent.repositories;

import org.springframework.data.repository.CrudRepository;

import com.educhoice.motherchoice.models.persistent.qna.WeeklyQuestionStatistics;

public interface WeeklyQuestionStatisticsRepository extends CrudRepository<WeeklyQuestionStatistics, Long> {

}
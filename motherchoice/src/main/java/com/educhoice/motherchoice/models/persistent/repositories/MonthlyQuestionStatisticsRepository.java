package com.educhoice.motherchoice.models.persistent.repositories;

import org.springframework.data.repository.CrudRepository;

import com.educhoice.motherchoice.models.persistent.qna.MonthlyQuestionStatistics;

public interface MonthlyQuestionStatisticsRepository extends CrudRepository<MonthlyQuestionStatistics, Long> {

}
package com.educhoice.motherchoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educhoice.motherchoice.models.persistent.qna.MonthlyQuestionStatistics;
import com.educhoice.motherchoice.models.persistent.qna.WeeklyQuestionStatistics;
import com.educhoice.motherchoice.models.persistent.repositories.MonthlyQuestionStatisticsRepository;
import com.educhoice.motherchoice.models.persistent.repositories.WeeklyQuestionStatisticsRepository;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionChartDto;


@Service
public class QuestionChartService {
	@Autowired
	private MonthlyQuestionStatisticsRepository monthQuestionsRepository;
	
	@Autowired
	private WeeklyQuestionStatisticsRepository weekQuestionsRepository;
	
	public QuestionChartDto retrieveAcademyQuestionChart(long accountId) {
		MonthlyQuestionStatistics monthlyQuestionStatistics = monthQuestionsRepository.findOne(accountId);
		WeeklyQuestionStatistics weeklyQuestionStatistics = weekQuestionsRepository.findOne(accountId);
		return new QuestionChartDto(monthlyQuestionStatistics,weeklyQuestionStatistics);
	}
}


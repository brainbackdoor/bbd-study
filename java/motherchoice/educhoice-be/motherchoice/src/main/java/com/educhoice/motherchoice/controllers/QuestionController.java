package com.educhoice.motherchoice.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.educhoice.motherchoice.service.AccountService;
import com.educhoice.motherchoice.service.QuestionChartService;
import com.educhoice.motherchoice.service.QuestionService;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.AnswerDetailsDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionChartDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionListDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionPostDto;

@RequestMapping("/question")
@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionChartService questionChartService;
	
	@Autowired
	private AccountService accountService;
	
	@CrossOrigin
	@ResponseBody
	@PostMapping("")
	public ResponseEntity inputQuestion(@RequestBody QuestionPostDto questionRequest) {
		questionService.saveQuestion(questionRequest);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	@CrossOrigin
	@ResponseBody
	@PostMapping("/bulkInput")
	public ResponseEntity bulkInputQuestion(@RequestBody List<QuestionPostDto> questionRequest) {
		questionRequest.stream().forEach(r -> questionService.saveQuestion(r));
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	// 나의 문의사항 / 답변보기 (부)
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/EO83b
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/gnbNb
	@GetMapping("/parent/list/{accountId}")
	public List<QuestionListDto> returnParentQuestionList(@PathVariable("accountId") long accountId) {
		return questionService.retrieveQuestionList(accountService.getAccountId(accountId));
	}

	// 답변목록 (부)
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/nsiG3
	@GetMapping("/parent/{questionId}")
	public QuestionDto returnParentQuestion(@PathVariable("questionId") long questionId) {
		return questionService.retrieveQuestion(questionId);
	}

	// 문의사항 / 통계 (원)
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/5stnD
	@GetMapping("/academy/questionList/{accountId}")
	public QuestionChartDto returnAcademyQuestionList(@PathVariable("accountId") long accountId) {
		return questionChartService.retrieveAcademyQuestionChart(accountId);
	}	
	
	// 학원답변 / 추가문의
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/TZWah
	@GetMapping("/answer/{answerId}")
	public AnswerDetailsDto returnParentAnswer(@PathVariable("answerId") long answerId) {
		return questionService.retrieveAnswer(answerId);
	}
	
	
}

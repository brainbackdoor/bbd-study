package com.educhoice.motherchoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.educhoice.motherchoice.configuration.security.service.UserJoinService;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.service.AcademyService;
import com.educhoice.motherchoice.service.QuestionService;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.AnswerPostDto;
import com.educhoice.motherchoice.valueobject.models.academies.inquiry.QuestionPostDto;

@RequestMapping("/api/bulk")
@RestController
public class BulkController {

        @Autowired
        private QuestionService questionService;

        @Autowired
        private UserJoinService userJoinService;

        @Autowired
        private AcademyService academyService;

        @CrossOrigin
        @ResponseBody
        @PostMapping("/questions")
        public ResponseEntity bulkInputQuestion(@RequestBody List<QuestionPostDto> questionRequest) {
                questionRequest.stream().forEach(r -> questionService.saveQuestion(r));
                return new ResponseEntity(HttpStatus.CREATED);
        }

        @CrossOrigin
        @ResponseBody
        @PostMapping("/answers")
        public ResponseEntity inputAnswer(@RequestBody List<AnswerPostDto> answerRequest) {
                answerRequest.stream().forEach(a -> questionService.saveAnswer(a));
                return new ResponseEntity(HttpStatus.CREATED);
        }

        @CrossOrigin
        @ResponseBody
        @PostMapping("/users")
        public ResponseEntity bulkInputUser(@RequestBody List<UserJoinRequest> userRequest) {
                userRequest.stream().forEach(u -> userJoinService.joinAccount(u));
                return new ResponseEntity(HttpStatus.CREATED);
        }

        @CrossOrigin
        @ResponseBody
        @PostMapping("/academies")
        public ResponseEntity bulkInputAcademyDetail(@RequestBody List<Academy> academyRequest) {
                academyRequest.stream().forEach(a -> academyService.saveAcademy(a));
                return new ResponseEntity(HttpStatus.CREATED);
        }

}
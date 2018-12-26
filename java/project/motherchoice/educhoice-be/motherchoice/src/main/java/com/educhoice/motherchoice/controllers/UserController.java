package com.educhoice.motherchoice.controllers;

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

@RequestMapping("/api/user")
@RestController
public class UserController {

        @Autowired
        private UserJoinService userJoinService;

        @CrossOrigin
        @ResponseBody
        @PostMapping("")
        public ResponseEntity inputUser(@RequestBody UserJoinRequest request) {
                        userJoinService.joinAccount(request);
                return new ResponseEntity(HttpStatus.CREATED);
        }
}
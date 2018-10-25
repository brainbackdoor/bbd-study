package com.elasticsearch.bbd.controller;

import com.elasticsearch.bbd.repository.EsRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiController {
    @Autowired
    private EsRepository esRepository;

    @GetMapping("/match/{index}/_search")
    public SearchResponse matchAllQuery(@PathVariable("index") String index) {
        return esRepository.matchAllQuery(new String[]{index});
    }
}

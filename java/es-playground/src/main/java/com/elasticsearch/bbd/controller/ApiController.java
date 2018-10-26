package com.elasticsearch.bbd.controller;

import com.elasticsearch.bbd.repository.EsRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ApiController {
    @Autowired
    private EsRepository esRepository;

    @GetMapping("/match/{index}/_search")
    public SearchResponse matchQuery(@PathVariable("index") String index,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String content) {
        if (content == null) return esRepository.matchAllQuery(new String[]{index});
        return esRepository.fullTextQuery(new String[]{index}, name, content);
    }
}

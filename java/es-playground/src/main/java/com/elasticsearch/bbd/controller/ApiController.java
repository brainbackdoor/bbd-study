package com.elasticsearch.bbd.controller;

import com.elasticsearch.bbd.repository.EsRepository;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ApiController {

    @Autowired
    private EsRepository esRepository;

    @GetMapping("/{index}/_search")
    public SearchResponse matchQuery(@PathVariable("index") String index,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String content) {
        if (content == null) return esRepository.matchAllQuery(new String[]{index});
        return esRepository.fullTextQuery(new String[]{index}, name, content);
    }

    @GetMapping("/{index}/_stats")
    public String stats(@PathVariable("index") String index) {
        return esRepository.stat(index).toString();
    }
}

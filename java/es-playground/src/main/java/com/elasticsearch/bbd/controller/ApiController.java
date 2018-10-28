package com.elasticsearch.bbd.controller;

import com.elasticsearch.bbd.repository.EsRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiController {

    @Autowired
    private EsRepository esRepository;

    @GetMapping("/{index}/_search")
    public SearchResponse match(@PathVariable("index") String index,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String content) {
        if (content == null) return esRepository.matchAll(new String[]{index});
        return esRepository.fullTextMatch(new String[]{index}, name, content);
    }

    @GetMapping("/aggregation")
    public List<? extends Terms.Bucket> aggregation(@RequestParam String term,
                                                    @RequestParam String field,
                                                    @RequestParam int size) {
        return esRepository.getBucket(term, field, size);
    }

    @GetMapping("/{index}/_stats")
    public String stats(@PathVariable("index") String index) {
        return esRepository.stat(index).toString();
    }
}

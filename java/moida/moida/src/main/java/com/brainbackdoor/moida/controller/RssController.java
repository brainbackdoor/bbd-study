package com.brainbackdoor.moida.controller;

import com.brainbackdoor.moida.model.rss.Feed;
import com.brainbackdoor.moida.model.rss.Rss;
import com.brainbackdoor.moida.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class RssController {

    @Autowired
    private RssService rssService;

    @GetMapping("/")
    public ResponseEntity init() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/rss")
    public Feed getFeed(@RequestBody Map map) throws Exception {
        return rssService.request(Rss.buildFeedItems(map));
    }

}

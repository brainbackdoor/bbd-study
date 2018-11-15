package com.brainbackdoor.moida.controller;

import com.brainbackdoor.moida.model.rss.RssFeed;
import com.brainbackdoor.moida.service.RssService;
import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
public class RssController {

    @Autowired
    private RssFeed view;

    @Autowired
    private RssService rssService;

    @GetMapping("/")
    public ResponseEntity init() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/rss")
    public SyndEntry getFeed(@RequestBody Map map
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        return rssService.request(view.buildFeedItems(map, request, response));
    }

}

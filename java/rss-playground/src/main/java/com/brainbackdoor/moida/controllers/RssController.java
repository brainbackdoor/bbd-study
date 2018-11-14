package com.brainbackdoor.moida.controllers;

import com.brainbackdoor.moida.model.RssFeed;
import com.rometools.rome.feed.rss.Item;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
public class RssController {

    @Autowired
    private RssFeed view;

    @GetMapping("/")
    public ResponseEntity init() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/rss")
    public List<Item> getFeed(@RequestBody Map map
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        return view.buildFeedItems(map, request, response);
    }

}

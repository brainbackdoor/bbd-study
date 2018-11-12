package com.brainbackdoor.moida.controllers;

import com.brainbackdoor.moida.model.RssFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;


@RestController
public class RssController {

    @Autowired
    private RssFeed view;

    @GetMapping("/rss")
    public View getFeed() {
        return view;
    }

}

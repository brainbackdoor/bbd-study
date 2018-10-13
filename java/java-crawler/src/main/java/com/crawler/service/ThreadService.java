package com.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;
import java.util.concurrent.Future;

public class ThreadService {

    @Async("threadPoolTaskExecutor")
    public Future<Document> send(String url) throws Exception {
        return new AsyncResult<Document>(getDocument(url));
    }

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

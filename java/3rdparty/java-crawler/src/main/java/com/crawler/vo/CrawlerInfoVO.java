package com.crawler.vo;

import com.opencsv.bean.CsvBindByPosition;

public class CrawlerInfoVO {

    @CsvBindByPosition(position = 0)
    private String search;

    public CrawlerInfoVO() {
    }

    public CrawlerInfoVO(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }
}

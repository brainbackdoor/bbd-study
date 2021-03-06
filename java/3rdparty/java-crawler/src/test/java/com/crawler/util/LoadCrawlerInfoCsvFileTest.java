package com.crawler.util;

import com.crawler.vo.CrawlerInfoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

class LoadCrawlerInfoCsvFileTest {

    LoadCrawlerInfoCsvFile loadCrawlerInfoCsvFile;

    @BeforeEach
    void setUp() {
        loadCrawlerInfoCsvFile = new LoadCrawlerInfoCsvFile();
    }

    @Test
    void loadTest() {
        List<CrawlerInfoVO> crawlerInfos = loadCrawlerInfoCsvFile.load();
        assertThat(crawlerInfos.size(), greaterThan(0));
        assertThat(crawlerInfos.get(0).getSearch(), is("가게"));
    }
}
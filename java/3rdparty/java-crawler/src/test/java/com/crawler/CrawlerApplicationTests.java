package com.crawler;

import com.crawler.vo.CrawlerInfoVO;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import java.net.URLDecoder;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CrawlerApplicationTests {

    @Test
    @DisplayName("Search result HTML img for first Thumb Image Test whether to import tag source")
    public void getFirstThumbImageTest() {
        CrawlerInfoVO crawlerInfo = new CrawlerInfoVO("클럭 미니 마사지기");
        String link = CrawlerApplication.getFirstThumbImageLink(crawlerInfo);
        assertThat(link, notNullValue());
    }

    @Test
    public void urlEncodingTest() throws MalformedURLException {
        CrawlerInfoVO crawlerInfo = new CrawlerInfoVO("클럭 미니 마사지기");
        URL encodedUrl = new URL(CrawlerApplication.getUrl(crawlerInfo));
        assertThat(URLDecoder.decode(encodedUrl.getQuery()), is("where=nexearch&query=클럭 미니 마사지기"));
    }

    @Test
    @Disabled
    public void mainTest() {
        String[] args = new String[0];
        CrawlerApplication.main(args);
    }
}

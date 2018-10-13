package com.brainbackdoor;

import com.brainbackdoor.vo.CrawlerInfoVO;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.net.URLDecoder;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BrainbackdoorApplicationTests {

    @Test
    @DisplayName("Search result HTML img for first Thumb Image Test whether to import tag source")
    public void getFirstThumbImageTest() {
        CrawlerInfoVO crawlerInfo = new CrawlerInfoVO("클럭 미니 마사지기");
        String link = BrainbackdoorApplication.getFirstThumbImageLink(crawlerInfo);
        assertThat(link,is("https://search.pstatic.net/common/?src=https%3A%2F%2Fditto-phinf.pstatic.net%2F20181005_11%2F1538726388639FW31w_JPEG%2Fc1ecbe146c255e3243a2e8f766f55e3c.jpeg&type=o&size=428x322"));
    }

    @Test
    public void urlEncodingTest() throws MalformedURLException {
        CrawlerInfoVO crawlerInfo = new CrawlerInfoVO("클럭 미니 마사지기");
        URL encodedUrl = new URL(BrainbackdoorApplication.getUrl(crawlerInfo));
        assertThat(URLDecoder.decode(encodedUrl.getQuery()),is("where=nexearch&query=클럭 미니 마사지기"));
    }
}

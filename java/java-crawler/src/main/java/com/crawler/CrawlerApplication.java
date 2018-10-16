package com.crawler;

import com.crawler.service.ThreadService;
import com.crawler.util.LoadCrawlerInfoCsvFile;
import com.crawler.util.PropertyLoader;
import com.crawler.vo.CrawlerInfoVO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@SpringBootApplication
public class CrawlerApplication {
    private static String DOWNLOAD_PATH = PropertyLoader.getInstance().getPropertyValue("DOWNLOAD_PATH");
    private static String EXTENSION = PropertyLoader.getInstance().getPropertyValue("EXTENSION");
    private static String SEARCH_URL = PropertyLoader.getInstance().getPropertyValue("SEARCH_URL");
    private static String ENC = PropertyLoader.getInstance().getPropertyValue("ENC");


    private static LoadCrawlerInfoCsvFile loadCrawlerInfoCsvFile = new LoadCrawlerInfoCsvFile();

    public static void main(String[] args) {
        List<CrawlerInfoVO> crawlerInfos = loadCrawlerInfoCsvFile.load();
        crawlerInfos.stream().parallel().forEach(v -> write(getFirstThumbImageLink(v), getFileName(v)));
    }

    private static String parse(Document doc) {
        Elements content = doc.getElementsByClass("thumb");
        Elements paras = content.select("div");
        Element firstPara = paras.get(0);
        firstPara.tagName("img").getElementsByAttribute("src").val("src");
        return firstPara.tagName("img").getElementsByAttribute("src").attr("src");
    }

    private static String getFileName(CrawlerInfoVO v) {
        return DOWNLOAD_PATH + v.getSearch() + "." + EXTENSION;
    }

    static String getFirstThumbImageLink(CrawlerInfoVO v) {
        try {
            ThreadService threadService = new ThreadService();
            Document doc = threadService.send(getUrl(v));
            return parse(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static void write(String link, String fileName) {
        try {
            ImageIO.write(getImage(link), EXTENSION, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage getImage(String link) throws IOException {
        return ImageIO.read(new URL(link));
    }

    static String getUrl(CrawlerInfoVO v) {
        String urlEncoding = null;
        try {
            urlEncoding = URLEncoder.encode(v.getSearch(), ENC);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SEARCH_URL + urlEncoding;
    }
}

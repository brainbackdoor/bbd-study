package com.brainbackdoor;

import com.brainbackdoor.util.LoadCrawlerInfoCsvFile;
import com.brainbackdoor.util.PropertyLoader;
import com.brainbackdoor.vo.CrawlerInfoVO;
import org.jsoup.Jsoup;
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
public class BrainbackdoorApplication {
    private static String DOWNLOAD_PATH = PropertyLoader.getInstance().getPropertyValue("DOWNLOAD_PATH");
    private static String EXTENSION = PropertyLoader.getInstance().getPropertyValue("EXTENSION");
    private static String SEARCH_URL = PropertyLoader.getInstance().getPropertyValue("SEARCH_URL");
    private static String ENC = PropertyLoader.getInstance().getPropertyValue("ENC");
    private static LoadCrawlerInfoCsvFile loadCrawlerInfoCsvFile = new LoadCrawlerInfoCsvFile();

    public static void main(String[] args) {

        List<CrawlerInfoVO> crawlerInfos = loadCrawlerInfoCsvFile.load();

        crawlerInfos.stream().forEach(v -> {
            Document doc = getDocument(getUrl(v));

            Elements content = doc.getElementsByClass("thumb");

            Elements paras = content.select("div");
            Element firstPara = paras.get(0);
            firstPara.tagName("img").getElementsByAttribute("src").val("src");
            String fileName = DOWNLOAD_PATH + v.getSearch() + "." +EXTENSION;
            write(firstPara, fileName);
        });
    }

    static void write(Element firstPara, String fileName) {
        try {
            ImageIO.write(getImage(firstPara), EXTENSION, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage getImage(Element firstPara) throws IOException {
        return ImageIO.read(new URL(firstPara.tagName("img").getElementsByAttribute("src").attr("src")));
    }

    static String getUrl(CrawlerInfoVO v) {
        String urlEncoding = null;
        try {
            urlEncoding = URLEncoder.encode(v.getSearch(), ENC);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncoding;
    }

    static Document getDocument(String urlEncoding) {
        String url = SEARCH_URL + urlEncoding ;
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

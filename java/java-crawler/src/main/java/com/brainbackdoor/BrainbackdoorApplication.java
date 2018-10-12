package com.brainbackdoor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BrainbackdoorApplication {
    private static String PATH = "C:/Users/brainbackdoor/Desktop/";
    private static String EXTENSION = ".gif";
    private static String SEARCH_URL = "http://search.naver.com/search.naver?where=nexearch&query=";

    public static void main(String[] args) {
        String search = "클럭 미니 마사지기";
        List<String> searchList = new ArrayList<>();
        searchList.add(search);

        searchList.stream().forEach(v -> {
            Document doc = getDocument(getUrl(v));

            Elements content = doc.getElementsByClass("thumb");

            Elements paras = content.select("div");
            Element firstPara = paras.get(0);
            firstPara.tagName("img").getElementsByAttribute("src").val("src");
            String fileName = PATH + v + EXTENSION;
            write(firstPara, fileName);
        });
    }

    static void write(Element firstPara, String fileName) {
        try {
            ImageIO.write(getImage(firstPara), "gif", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage getImage(Element firstPara) throws IOException {
        return ImageIO.read(new URL(firstPara.tagName("img").getElementsByAttribute("src").attr("src")));
    }

    static String getUrl(String v) {
        String urlEncoding = null;
        try {
            urlEncoding = URLEncoder.encode(v, "UTF-8");
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

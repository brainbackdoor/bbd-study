package com.crawler.util;

import java.io.IOException;
import java.io.Reader;
import com.crawler.vo.CrawlerInfoVO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoadCrawlerInfoCsvFile {
    private String crawlerInfoCsvFilePath;

    public LoadCrawlerInfoCsvFile() {
        crawlerInfoCsvFilePath = PropertyLoader.getInstance().getPropertyValue("CRAWLER_INFO_CSV_FILE_PATH");
    }

    public List<CrawlerInfoVO> load() {
        List<CrawlerInfoVO> crawlerInfos = null;
        try(Reader reader = Files.newBufferedReader(Paths.get(crawlerInfoCsvFilePath))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CrawlerInfoVO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            crawlerInfos = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crawlerInfos;
    }
}

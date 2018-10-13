package com.brainbackdoor.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PropertyLoaderTest {

    @Test
    @DisplayName("Test to load application.properties data")
    void loadPropertiesTest() {
        String crawlerInfoCsvFilePath = PropertyLoader.getInstance().getPropertyValue("CRAWLER_INFO_CSV_FILE_PATH");
        assertThat(crawlerInfoCsvFilePath, is("src/main/resources/crawler_Info.csv"));
    }
}
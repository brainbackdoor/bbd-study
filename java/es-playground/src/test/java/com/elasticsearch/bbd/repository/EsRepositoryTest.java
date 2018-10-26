package com.elasticsearch.bbd.repository;

import com.elasticsearch.bbd.config.EsConfig;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EsRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(EsRepositoryTest.class);
    private final String DEFAULT_INDEX = "news";
    private final String CONTENT = "content";

    @Autowired
    private EsRepository esRepository;

    @Test
    public void connectionTest() {
        assertThat(esRepository.client.transportAddresses().toString(), is("127.0.0.1:9300"));
    }

    @Test
    @DisplayName("Match All Query")
    public void matchAllQueryTest() {
        assertThat(esRepository.matchAllQuery(new String[]{DEFAULT_INDEX}).status(), is(RestStatus.OK));
    }

    @Test
    @DisplayName("Since the standard tokenizer is used by default, the contents are searched after trimming the contents to a blank basis.")
    public void fullTextQueryTest() {
        assertThat(esRepository.fullTextQuery(new String[]{DEFAULT_INDEX}, CONTENT, "자바 개발자")
                .getHits().getAt(0)
                .getSourceAsMap().get(CONTENT).toString()
                .indexOf("자바"), greaterThan(1));
    }

    @Test
    @DisplayName("Query index information")
    public void statTest() {
        assertThat(esRepository.stat(DEFAULT_INDEX).getShards().length, is(5));
    }
}
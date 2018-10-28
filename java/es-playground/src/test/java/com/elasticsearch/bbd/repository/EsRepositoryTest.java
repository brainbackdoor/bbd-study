package com.elasticsearch.bbd.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
        assertThat(esRepository.matchAll(new String[]{DEFAULT_INDEX}).status(), is(RestStatus.OK));
    }

    @Test
    @DisplayName("Since the standard tokenizer is used by default, " +
            "the contents are searched after trimming the contents to a blank basis.")
    public void fullTextQueryTest() {
        assertThat(esRepository.fullTextMatch(new String[]{DEFAULT_INDEX}, CONTENT, "자바 개발자")
                .getHits().getAt(0)
                .getSourceAsMap().get(CONTENT).toString()
                .indexOf("자바"), greaterThan(1));
    }

    @Test
    public void fullTestQueryResultTest() {
        SearchResponse response = esRepository.fullTextMatch(
                new String[]{DEFAULT_INDEX}, CONTENT, "자바 개발자");
        List result = Arrays.asList(response.getHits().getHits());
        assertThat(result.size(), greaterThan(0));
    }

    @Test
    @DisplayName("Field-based aggregate function")
    public void aggrQueryTest() {
        SearchResponse response = esRepository.aggrTermQuery("keywords", "keywords", 20);
        assertThat(response.getAggregations().getAsMap().size(), greaterThan(0));
    }

    @Test
    @DisplayName("Extract Bucket Information from Aggregate Function Results")
    public void getBucketAggrTest() {
        List<? extends Terms.Bucket> buckets = esRepository.getBucket("keywords", "keywords", 20);
        assertThat(buckets.get(0).getKey(), is("시장,5615"));
    }

    public Map<String, Object> toMap(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(mapper.writeValueAsString(obj), new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    @DisplayName("Query index information")
    public void statTest() {
        assertThat(esRepository.stat(DEFAULT_INDEX).getShards().length, is(5));
    }
}
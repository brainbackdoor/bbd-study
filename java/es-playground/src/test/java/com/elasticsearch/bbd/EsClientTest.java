package com.elasticsearch.bbd;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
class EsClientTest {

/*
2018-07-19 ~ 2018-10-19 까지
전자신문 / 디지털 타임즈의 기사를 모아둔 자료를 바탕으로 테스트
자료출처는 https://www.bigkinds.or.kr/ 에서 다운로드 가능

Elasticsearch 구동 후
# java -jar news-data-importer.jar
 */
    private TransportClient client;

    @BeforeEach
    void setUp() throws UnknownHostException {
        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @Test
    @DisplayName("🚀 Elasticsearch Connection")
    void connectionTest() {
        assertThat(client.transportAddresses().get(0).toString(), is("127.0.0.1:9300"));
    }

    @Test
    @DisplayName("Match All Query")
    void matchAllQueryTest() {
        SearchResponse response = client
                .prepareSearch("news")
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        assertThat(response.status(), is(RestStatus.OK));
    }

    @Test
    void fullTextQueryTest() {
        SearchResponse response = client
                .prepareSearch("news")
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("content","자바 개발자"))
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        assertThat(Integer.valueOf(Long.toString(response.getHits().totalHits)), greaterThan(0));
    }

    @AfterEach
    void tearDown() {
        client.close();
    }

}
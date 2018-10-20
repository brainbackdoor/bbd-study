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
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
class EsClientTest {


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
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())                 // Query
                .setFrom(0).setSize(10).setExplain(true)
                .get();
        assertThat(response.status(), is(RestStatus.OK));
    }

    @AfterEach
    void tearDown() {
        client.close();
    }

}
package com.elasticsearch.bbd.repository;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class EsRepository {
    TransportClient client;

    @Autowired
    public EsRepository(TransportClient client) {
        this.client = client;
    }

    public SearchResponse matchAllQuery(String... index) {
        return client
                .prepareSearch(index)
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(0).setSize(10).setExplain(true)
                .get();
    }

    public SearchResponse fullTextQuery(String[] index, String content) {
        return client
                .prepareSearch(index)
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("content",content))
                .setFrom(0).setSize(10).setExplain(true)
                .get();
    }
}

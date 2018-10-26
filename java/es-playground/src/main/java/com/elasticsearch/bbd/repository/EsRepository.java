package com.elasticsearch.bbd.repository;

import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public SearchResponse fullTextQuery(String[] index, String name, String content) {
        return client
                .prepareSearch(index)
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFetchSource(new String[]{"title", "content"}, null)
                .setQuery(QueryBuilders.matchQuery(name, content))
                .get();
    }

    public IndicesStatsResponse stat(String index) {
        return client.admin().indices().prepareStats(index).get();
    }
}

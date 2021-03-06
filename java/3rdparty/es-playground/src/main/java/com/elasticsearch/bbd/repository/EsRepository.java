package com.elasticsearch.bbd.repository;

import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EsRepository {
    TransportClient client;

    @Autowired
    public EsRepository(TransportClient client) {
        this.client = client;
    }

    public SearchResponse matchAll(String... index) {
        return client
                .prepareSearch(index)
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery())
                .setFrom(0).setSize(10).setExplain(true)
                .get();
    }

    public SearchResponse fullTextMatch(String[] index, String name, String content) {
        return client
                .prepareSearch(index)
                .setTypes()
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFetchSource(new String[]{"title", "press", "content", "pubDate"}, null)
                .setQuery(QueryBuilders.matchQuery(name, content))
                .get();
    }

    public SearchResponse aggrTermQuery(String term, String field, int size) {
        return client.prepareSearch()
                .addAggregation(AggregationBuilders.terms(term).field(field).size(size).showTermDocCountError(true))
                .get();
    }

    public IndicesStatsResponse stat(String index) {
        return client.admin().indices().prepareStats(index).get();
    }

    public List getBucket(String term, String field, int size) {
        Terms terms = aggrTermQuery(term, field, size).getAggregations().get(term);
        return terms.getBuckets().stream()
                .map((k) -> (((Terms.Bucket) k).getKey() + "," + ((Terms.Bucket) k).getDocCount()))
                .collect(Collectors.toList());
    }

    public SearchResponse suggestQuery(String[] index, String name, String field, String text) {
        return client.prepareSearch(index)
                .suggest(new SuggestBuilder()
                        .addSuggestion(name, SuggestBuilders.termSuggestion(field).text(text))).get();
    }
}

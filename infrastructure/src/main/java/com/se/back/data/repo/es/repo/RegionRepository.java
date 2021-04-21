package com.se.back.data.repo.es.repo;

import com.se.back.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 信长华
 * @date: 2021/4/20 13:09
 * @className: RegionRepository
 * @description: TODO
 * @version: 1.0
 */
@Repository
@Slf4j
public class RegionRepository {
    private final RestHighLevelClient singleNodeRestHighLevelClient;
    private final RestHighLevelClient clusterHighLevelClient;

    public RegionRepository(
            @Qualifier(value = "singleNodeHighLevelClient") RestHighLevelClient singleNodeRestHighLevelClient,
            @Qualifier(value = "clusterHighLevelClient") RestHighLevelClient clusterHighLevelClient) {
        this.singleNodeRestHighLevelClient = singleNodeRestHighLevelClient;
        this.clusterHighLevelClient = clusterHighLevelClient;
    }

    public <T> List<T> searchStringQuery(String index, String region, Class<T> resultType) throws IOException {
        SearchSourceBuilder searchBuilder = makeSearchQueryStringQuerySourceBuilder(region);
        SearchRequest searchRequest = new SearchRequest(index).source(searchBuilder);
        List<T> results = new ArrayList<>();
        SearchResponse response = singleNodeRestHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            results.add(JsonUtil.makeObject(sourceAsString, resultType));
        }
        return results;

    }

    private SearchSourceBuilder makeSearchQueryStringQuerySourceBuilder(String region) {
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(region).field("region");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(queryBuilder);
        sourceBuilder.size(100);
        return sourceBuilder;
    }
}

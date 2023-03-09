package com.cricketApplication.dao.elasticSearchRepository.Impl;



import co.elastic.clients.elasticsearch._types.aggregations.AggregationBuilders;
import com.cricketApplication.dao.entities.BallDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.Aggregation;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.Query;

import static co.elastic.clients.elasticsearch._types.aggregations.AggregationBuilders.terms;
import static org.springframework.data.elasticsearch.client.elc.QueryBuilders.matchQuery;
import static org.springframework.data.elasticsearch.client.elc.QueryBuilders.queryStringQuery;

public class ElasticBallDataRepositoryImpl {
//    @Autowired
//    @Lazy
//    private ElasticsearchOperations operations;
//
//    public SearchPage<BallDataDao> findNoBallCountByGameId(Long gameId, Pageable pageable) {
//        String q = "N";
//
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(queryStringQuerymatchQuery("title", "elasticsearch data",null,null))
//                .build();  // send it of and get the result
//
//        return SearchHitSupport.searchPageFor(searchHits, pageable);  // convert the result to a SearchPage
//    }
}

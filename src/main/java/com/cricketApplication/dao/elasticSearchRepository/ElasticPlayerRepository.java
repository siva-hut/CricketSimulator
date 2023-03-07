package com.cricketApplication.dao.elasticSearchRepository;

import com.cricketApplication.dao.entities.PlayerDao;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticPlayerRepository extends ElasticsearchRepository<PlayerDao, Long> {
    List<PlayerDao> findByName(String name);
}
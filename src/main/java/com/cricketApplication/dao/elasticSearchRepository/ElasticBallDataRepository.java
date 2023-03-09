package com.cricketApplication.dao.elasticSearchRepository;

import com.cricketApplication.dao.entities.BallDataDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticBallDataRepository extends ElasticsearchRepository<BallDataDao,Long> {
    List<BallDataDao> findByGameId(Long gameId);
    SearchPage<BallDataDao> findNoBallCountByGameId(Long gameId, Pageable pageable);
}

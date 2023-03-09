package com.cricketApplication.dao.elasticSearchRepository;


import com.cricketApplication.dao.entities.TeamStatsDao;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticTeamStatsRepository extends ElasticsearchRepository<TeamStatsDao, Long> {
}

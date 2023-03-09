package com.cricketApplication.dao.elasticSearchRepository;

import com.cricketApplication.dao.entities.TeamDao;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchTeamRepository extends ElasticsearchRepository<TeamDao,String> {
}

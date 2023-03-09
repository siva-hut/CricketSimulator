package com.cricketApplication.dao.elasticSearchRepository;

import com.cricketApplication.dao.entities.PlayerDao;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticPlayerRepository extends ElasticsearchRepository<PlayerDao, Long> {
    @Query ("{\n" +
            "    \"match\": {\n" +
            "      \"name\": \"?0\"\n" +
            "    }\n" +
            "  }")
    List<PlayerDao> findByName(String name);
    @Query ("{\n" +
            "    \"match\": {\n" +
            "      \"name.innerField\": \"?0\"\n" +
            "    }\n" +
            "  }")
    List<PlayerDao> findByNameTrue(String name);

    @Query("{\"bool\":{\"must\":" +
            "[{\"wildcard\":{\"name\":{\"value\":\"*?0*\"}}}],\"" +
            "filter\":[{\"bool\":{\"must\":[{\"match\":{\"teamName\":\"?1\"}}]}}]}}\n")
    List<PlayerDao>findByNameAndTeamName(String name,String teamName);

}
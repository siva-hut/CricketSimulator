package com.cricketApplication.dao.elasticSearchRepository;

import com.cricketApplication.dao.entities.GameDao;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ElasticGameRepository extends ElasticsearchRepository<GameDao,Long> {
    @Query("{\"bool\": {\n" +
            "      \"should\": [\n" +
            "        {\"wildcard\": {\n" +
            "          \"firstBattingTeamName.keyword\": \"*?0*\"\n" +
            "        }},{\n" +
            "          \"wildcard\": {\n" +
            "            \"firstBowlingTeamName.keyword\": \"*?1*\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }}")
    public List<GameDao> getGameByTeamName(String teamName,String teamNameDup);
    @Query("{\"bool\": {\n" +
            "      \"should\": [\n" +
            "        {\"wildcard\": {\n" +
            "          \"firstBattingTeamName.keyword\": \"?0\"\n" +
            "        }},{\n" +
            "          \"wildcard\": {\n" +
            "            \"firstBowlingTeamName.keyword\": \"?1\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }}")
    public List<GameDao> getTrueGameByTeamName(String teamName,String teamNameDup);
}

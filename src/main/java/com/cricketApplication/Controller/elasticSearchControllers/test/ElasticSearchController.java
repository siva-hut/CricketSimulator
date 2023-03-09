package com.cricketApplication.Controller.elasticSearchControllers.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.cricketApplication.dao.elasticSearchRepository.ElasticPlayerRepository;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.entities.TeamDao;
import com.cricketApplication.dao.repositories.TeamRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/es")
public class ElasticSearchController {
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ElasticPlayerRepository elasticPlayerRepository;
    public List<TeamDao> fetchGamesWithId(Long gameId) throws IOException {
        List<Query> queries = prepareQueryList(gameId);
        SearchResponse<TeamDao> employeeSearchResponse = elasticsearchClient.search(req->
                        req.index("game")
                                .size(10),
                TeamDao.class);
        return employeeSearchResponse.hits().hits().stream()
                .map(Hit::source).collect(Collectors.toList());
    }
    private List<Query> prepareQueryList(Long gameId) {
        Map<String, String> conditionMap = new HashMap<>();
        conditionMap.put("_class.keyword","com.cricketApplication.dao.entities.TeamDao");
        return conditionMap.entrySet()
                .stream()
                .filter(entry->!ObjectUtils.isEmpty(entry.getValue()))
                .map(entry-> QueryBuilderUtils.termQuery(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
    @GetMapping("/getGameDetails")
    public List<TeamDao> get() throws IOException {
        return fetchGamesWithId(1L);
    }
    @GetMapping("/getPlayers")
    public List<PlayerDao> getAllPlayer(){
        return (List<PlayerDao>) elasticPlayerRepository.findByName("Angeline");
    }
}

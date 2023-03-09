package com.cricketApplication.service.elasticSearchServices;

import com.cricketApplication.dao.elasticSearchRepository.ElasticPlayerRepository;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dataModels.response.getAll.GetAllPlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsPlayerDetailService {
    @Autowired
    private ElasticPlayerRepository elasticPlayerRepository;

    public GetAllPlayerResponse getPlayerMatch(String playerName){
        List<PlayerDao> playerDaoList = elasticPlayerRepository.findByName(playerName);
        return GetAllPlayerResponse.getAllPlayerResponse(playerDaoList);
    }
    public GetAllPlayerResponse getTruePlayerMatch(String playerName){
        List<PlayerDao> playerDaoList = elasticPlayerRepository.findByNameTrue(playerName);
        return GetAllPlayerResponse.getAllPlayerResponse(playerDaoList);
    }
    public GetAllPlayerResponse getPlayerSubstringByTeamName(String playerName,String teamName){
        List<PlayerDao> playerDaoList = elasticPlayerRepository.findByNameAndTeamName(playerName,teamName);
        return GetAllPlayerResponse.getAllPlayerResponse(playerDaoList);
    }
}

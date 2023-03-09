package com.cricketApplication.service.elasticSearchServices;

import com.cricketApplication.dao.elasticSearchRepository.ElasticGameRepository;
import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dataModels.response.getAll.GetAllGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsGameDetailService {
    @Autowired
    private ElasticGameRepository elasticGameRepository;

    public GetAllGameResponse getGameMatchByTeamName(String teamName){

        List<GameDao> gameDaos = elasticGameRepository.getGameByTeamName(teamName,teamName);
        return GetAllGameResponse.getAllGameResponse(gameDaos);
    }
    public GetAllGameResponse getTrueGameMatchByTeamName(String teamName){
        List<GameDao> gameDaos = elasticGameRepository.getTrueGameByTeamName(teamName,teamName);
        return GetAllGameResponse.getAllGameResponse(gameDaos);
    }
}

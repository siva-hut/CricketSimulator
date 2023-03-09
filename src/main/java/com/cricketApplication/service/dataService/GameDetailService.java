package com.cricketApplication.service.dataService;

import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.entities.TeamStatsDao;
import com.cricketApplication.dao.repositories.GameRepository;
import com.cricketApplication.dataModels.response.get.GetGameDetailResponse;
import com.cricketApplication.dataModels.response.get.GetGameResponse;
import com.cricketApplication.dataModels.response.getAll.GetAllGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameDetailService {
    @Autowired
    private GameRepository gameRepository;

    //Convert GameDao to Game Response

    public GetAllGameResponse getAllGames() {
        List<GameDao> gameDaoList = gameRepository.findAll();
        return GetAllGameResponse.getAllGameResponseFromGameDao(gameDaoList);
    }

    public GetAllGameResponse getAllActiveGames() {
        List<GameDao> gameDaoList = gameRepository.findByGameActive(true);
        return GetAllGameResponse.getAllGameResponseFromGameDao(gameDaoList);
    }

    public GetGameDetailResponse getGameDetails(Long gameId) {
        GameDao gameDao = gameRepository.findById(gameId).get();
        List<TeamStatsDao> teamStatsDaoList = gameDao.getTeamStatsDaos();
        TeamStatsDao team1 = teamStatsDaoList.get(0);
        TeamStatsDao team2 = teamStatsDaoList.get(1);
        return GetGameDetailResponse.getGameDetailResponse(gameDao,team1,team2);
    }

}

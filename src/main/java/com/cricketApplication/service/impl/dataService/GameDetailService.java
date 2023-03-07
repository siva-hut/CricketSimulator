package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.entities.TeamStatsDao;
import com.cricketApplication.dao.repositories.GameRepository;
import com.cricketApplication.dataModels.response.GameDetailResponse;
import com.cricketApplication.dataModels.response.GameResponse;
import com.cricketApplication.dataModels.response.GetAllGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameDetailService {
    @Autowired
    private GameRepository gameRepository;

    //Convert GameDao to Game Response
    private List<GameResponse> convert(List<GameDao> gameDaoList) {
        List<GameResponse> gameResponseList = new ArrayList<>();
        for (GameDao gameDao : gameDaoList) {
            gameResponseList.add(GameResponse.getGameResponse(gameDao));
        }
        return gameResponseList;
    }

    public GetAllGameResponse getAllGames() {
        List<GameDao> gameDaoList = gameRepository.findAll();
        List<GameResponse> gameResponseList = convert(gameDaoList);
        return GetAllGameResponse.getAllGameResponse(gameResponseList);
    }

    public GetAllGameResponse getAllActiveGames() {
        List<GameDao> gameDaoList = gameRepository.findByGameActive(true);
        List<GameResponse> gameResponseList = convert(gameDaoList);
        return GetAllGameResponse.getAllGameResponse(gameResponseList);
    }

    public GameDetailResponse getGameDetails(Long gameId) {
        GameDao gameDao = gameRepository.findById(gameId).get();
        List<TeamStatsDao> teamStatsDaoList = gameDao.getTeamStatsDaos();
        TeamStatsDao team1 = teamStatsDaoList.get(0);
        TeamStatsDao team2 = teamStatsDaoList.get(1);
        return GameDetailResponse.getGameDetailResponse(gameDao,team1,team2);
    }

}

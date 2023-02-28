package com.circketApplication.service.impl;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.dao.entities.BallDataDao;
import com.circketApplication.dao.entities.GameDao;
import com.circketApplication.dao.entities.GamePlayerDetails;
import com.circketApplication.dao.entities.PlayerDao;
import com.circketApplication.dao.repositories.BallDataRepository;
import com.circketApplication.dao.repositories.GamePlayerDetailsRepository;
import com.circketApplication.dao.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReloadGameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerDetailsRepository gamePlayerDetailsRepository;
    @Autowired
    private BallDataRepository ballDataRepository;
    public Game reloadGame(Long gameId) {
        GameDao gameDao = gameRepository.findById(gameId).get();
        return reloadGame(gameDao);
    }
    private List<PlayerDao> getPlayers(Long gameId, String teamName){
        List<PlayerDao> playerDaoList = new ArrayList<>();
        List<GamePlayerDetails> gamePlayerDetailsList = gamePlayerDetailsRepository.findByGameIdAndTeamNameOrderByIdAsc(gameId,teamName);
        for (GamePlayerDetails gamePlayerDetails:gamePlayerDetailsList) {
            playerDaoList.add(gamePlayerDetails.getPlayerDao());
        }
        return  playerDaoList;
    }
    public Game reloadGame(GameDao gameDao) {
        gameDao.setGameActive(true);
        gameRepository.save(gameDao);
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.setTotalOvers(gameDao.getTotalOvers());
        gameBuilder.setTeam1Name(gameDao.getFirstBattingTeamName());
        gameBuilder.setTeam2Name(gameDao.getFirstBowlingTeamName());
        Game game = gameBuilder.getGame();
        game.setId(gameDao.getId());
        game.getBattingTeam().createTeamPlayers(getPlayers(game.getId(),game.getBattingTeam().getTeamName()));
        game.getBowlingTeam().createTeamPlayers(getPlayers(game.getId(),game.getBowlingTeam().getTeamName()));
        List<BallDataDao> ballDataDaoList = ballDataRepository.findByGameIdOrderByInnings(game.getId());
        for (BallDataDao ballDataDao:ballDataDaoList) {
            game.simulateNextBall(ballDataDao.getBallOutCome());
        }
        return game;
    }
}

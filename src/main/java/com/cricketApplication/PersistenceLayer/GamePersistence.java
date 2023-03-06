package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.repositories.*;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
@Component
public class GamePersistence {
    @Autowired
    @Lazy
    private GameRepository gameRepository;
    @Autowired
    @Lazy
    private TeamPersistence teamPersistence;
    @Autowired
    @Lazy
    private GamePlayerDetailsPersistence gamePlayerDetailsPersistence;
    @Autowired
    @Lazy
    private PlayerPersistence playerPersistence;
    @Autowired
    @Lazy
    private PlayerStatsPersistence playerStatsPersistence;
    @Autowired
    @Lazy
    private TeamStatsPersistence teamStatsPersistence;
    public GameDao save(GameDao gameDao){
        return gameRepository.save(gameDao);
    }
    public GameDao findById(Long id){
        return gameRepository.findById(id).get();
    }
    //Create the game and start
    public void persistGameCreation(Game game) {
        persistGameCreation(game, new Date(), true);
    }

    //Create the game, create or load the teams, create or load the Players
    private void persistGameCreation(Game game, Date date, boolean gameActive) {
        teamPersistence.persistAndLoadPlayers(game.getBattingTeam());
        teamPersistence.persistAndLoadPlayers(game.getBowlingTeam());
        GameDao gameDao = GameDao.builder()
                .firstBattingTeamName(game.getBattingTeam().getTeamName())
                .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                .totalOvers(game.getOvers().getTotalOvers())
                .startDate(new Timestamp(date.getTime()))
                .gameActive(gameActive)
                .build();
        gameRepository.save(gameDao);
        game.setId(gameDao.getId());
        gamePlayerDetailsPersistence.setMatchPlayerDetails(game.getBattingTeam(), game.getId());
        gamePlayerDetailsPersistence.setMatchPlayerDetails(game.getBowlingTeam(), game.getId());
    }

    //Create the game and start in the future
    public void persistGameCreation(Game game, Date date) {
        persistGameCreation(game, date, false);
    }

    //Saving Player and Team details after the game is over
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void persistGameOnCompletion(Game game) {
        updatePlayerAndPlayerStats(game);
        GameDao gameDao = gameRepository.findById(game.getId()).get();
        gameDao.setEndDate(new Timestamp(System.currentTimeMillis()));
        gameDao.setGameActive(false);
        gameRepository.save(gameDao);
        teamPersistence.updateTeam(game.getBattingTeam(), game.getBowlingTeam());
        teamStatsPersistence.updateTeamStats(game.getBowlingTeam(), game.getBattingTeam().getBattingOvers(), game.getId());
        teamStatsPersistence.updateTeamStats(game.getBattingTeam(), game.getBowlingTeam().getBattingOvers(), game.getId());
    }

    private void updatePlayerAndPlayerStats(Game game) {
        updatePlayersAndPlayerStatsInTeam(game.getBattingTeam(), game.getId());
        updatePlayersAndPlayerStatsInTeam(game.getBowlingTeam(), game.getId());
    }

    private void updatePlayersAndPlayerStatsInTeam(Team team, Long id) {
        for (Player player : team.getPlayers()) {
            playerPersistence.updatePlayer(player, team.getTeamName());
        }
        for (Player player : team.getPlayers()) {
            playerStatsPersistence.updatePlayerStats(player, id);
        }
    }
}

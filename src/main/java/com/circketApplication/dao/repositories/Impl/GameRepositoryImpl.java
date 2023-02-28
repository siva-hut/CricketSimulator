package com.circketApplication.dao.repositories.Impl;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.cricketGame.Team;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.dao.entities.*;
import com.circketApplication.dao.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GameRepositoryImpl {
    @Autowired
    @Lazy
    private GameRepository gameRepository;
    @Autowired
    @Lazy
    private TeamRepository teamRepository;
    @Autowired
    @Lazy
    private GamePlayerDetailsRepository gamePlayerDetailsRepository;
    @Autowired
    @Lazy
    private PlayerRepository playerRepository;
    @Autowired
    @Lazy
    private PlayerStatsRepository playerStatsRepository;
    @Autowired
    @Lazy
    private TeamStatsRepository teamStatsRepository;
    public void persistGameCreation(Game game) {
        if(game.getId()==null) {
            teamRepository.persistAndLoadPlayers(game.getBattingTeam());
            teamRepository.persistAndLoadPlayers(game.getBowlingTeam());
            GameDao gameDao = GameDao.builder()
                    .firstBattingTeamName(game.getBattingTeam().getTeamName())
                    .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                    .totalOvers(game.getOvers().getTotalOvers())
                    .startDate(new Timestamp(System.currentTimeMillis()))
                    .gameActive(false)
                    .build();
            gameRepository.save(gameDao);
            game.setId(gameDao.getId());
            gamePlayerDetailsRepository.setMatchPlayerDetails(game.getBattingTeam(),game.getId());
            gamePlayerDetailsRepository.setMatchPlayerDetails(game.getBowlingTeam(),game.getId());
        }
    }

    public void persistGameCreation(Game game, Date date) {
        if(game.getId()==null) {
            teamRepository.persistAndLoadPlayers(game.getBattingTeam());
            teamRepository.persistAndLoadPlayers(game.getBowlingTeam());
            GameDao gameDao = GameDao.builder()
                    .firstBattingTeamName(game.getBattingTeam().getTeamName())
                    .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                    .totalOvers(game.getOvers().getTotalOvers())
                    .startDate(new Timestamp(date.getTime()))
                    .gameActive(false)
                    .build();
            gameRepository.save(gameDao);
            game.setId(gameDao.getId());
            gamePlayerDetailsRepository.setMatchPlayerDetails(game.getBattingTeam(),game.getId());
            gamePlayerDetailsRepository.setMatchPlayerDetails(game.getBowlingTeam(),game.getId());
        }
    }
    private void updatePlayersAndPlayerStatsInTeam(Team team, Long id)
    {
        for (Player player: team.getPlayers()){
            playerRepository.updatePlayer(player, team.getTeamName());
        }
        for (Player player: team.getPlayers()){
            playerStatsRepository.updatePlayerStats(player,id);
        }
    }
    private void updatePlayerAndPlayerStats(Game game)
    {
        updatePlayersAndPlayerStatsInTeam(game.getBattingTeam(),game.getId());
        updatePlayersAndPlayerStatsInTeam(game.getBowlingTeam(),game.getId());
    }
    public void persistGameOnCompletion(Game game) {
        updatePlayerAndPlayerStats(game);
        GameDao gameDao = gameRepository.findById(game.getId()).get();
        gameDao.setEndDate(new Timestamp(System.currentTimeMillis()));
        gameDao.setGameActive(false);
        gameRepository.save(gameDao);
        teamRepository.updateTeam(game.getBattingTeam(),game.getBowlingTeam());
        teamStatsRepository.updateTeamStats(game.getBowlingTeam(),game.getBattingTeam().getBattingOvers(),game.getId());
        teamStatsRepository.updateTeamStats(game.getBattingTeam(),game.getBowlingTeam().getBattingOvers(),game.getId());
    }
}

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
    //Create the game and start
    public void persistGameCreation(Game game) {
        persistGameCreation(game,new Date(),false);
    }
    //Create the game and start in the future
    public void persistGameCreation(Game game, Date date) {
        persistGameCreation(game,date,false);
    }
    //Create the game, create or load the teams, create or load the Players
    private void persistGameCreation(Game game, Date date,boolean gameActive){
        teamRepository.persistAndLoadPlayers(game.getBattingTeam());
        teamRepository.persistAndLoadPlayers(game.getBowlingTeam());
        GameDao gameDao = GameDao.builder()
                .firstBattingTeamName(game.getBattingTeam().getTeamName())
                .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                .totalOvers(game.getOvers().getTotalOvers())
                .startDate(new Timestamp(date.getTime()))
                .gameActive(gameActive)
                .build();
        gameRepository.save(gameDao);
        game.setId(gameDao.getId());
        gamePlayerDetailsRepository.setMatchPlayerDetails(game.getBattingTeam(),game.getId());
        gamePlayerDetailsRepository.setMatchPlayerDetails(game.getBowlingTeam(),game.getId());
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
    //Saving Player and Team details after the game is over
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

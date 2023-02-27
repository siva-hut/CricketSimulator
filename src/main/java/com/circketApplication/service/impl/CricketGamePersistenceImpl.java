package com.circketApplication.service.impl;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.cricketGame.Team;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.util.Overs;
import com.circketApplication.dao.entities.*;
import com.circketApplication.dao.repositories.*;
import com.circketApplication.service.interfaces.CricketGamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CricketGamePersistenceImpl implements CricketGamePersistence {
    @Autowired
    private PlayerStatsRepository playerStatsRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BallDataRepository ballDataRepository;
    @Autowired
    private TeamStatsRepository teamStatsRepository;
    @Autowired
    private GamePlayerDetailsRepository gamePlayerDetailsRepository;

    @Override
    public void persistGameCreation(Game game) {
        if(game.getId()==null) {
            persistAndLoadPlayers(game.getBattingTeam());
            persistAndLoadPlayers(game.getBowlingTeam());
            GameDao gameDao = GameDao.builder()
                    .firstBattingTeamName(game.getBattingTeam().getTeamName())
                    .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                    .totalOvers(game.getOvers().getTotalOvers())
                    .startDate(new Timestamp(System.currentTimeMillis()))
                    .gameActive(false)
                    .build();
            gameRepository.save(gameDao);
            game.setId(gameDao.getId());
            setMatchPlayerDetails(game.getBattingTeam(),game.getId());
            setMatchPlayerDetails(game.getBowlingTeam(),game.getId());
        }
    }

    @Override
    public void persistGameCreation(Game game, Date date) {
        if(game.getId()==null) {
            persistAndLoadPlayers(game.getBattingTeam());
            persistAndLoadPlayers(game.getBowlingTeam());
            GameDao gameDao = GameDao.builder()
                    .firstBattingTeamName(game.getBattingTeam().getTeamName())
                    .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                    .totalOvers(game.getOvers().getTotalOvers())
                    .startDate(new Timestamp(date.getTime()))
                    .gameActive(false)
                    .build();
            gameRepository.save(gameDao);
            game.setId(gameDao.getId());
            setMatchPlayerDetails(game.getBattingTeam(),game.getId());
            setMatchPlayerDetails(game.getBowlingTeam(),game.getId());
        }
    }

    @Override
    public void persist(String teamName) {
        teamRepository.save(TeamDao.builder().name(teamName).build());
    }

    //Used to load player IDs into team players on creation
    private void persistAndLoadPlayers(Team team) {
        if(teamRepository.findByName(team.getTeamName())==null)
        {
            TeamDao teamDao = TeamDao.builder()
                            .name(team.getTeamName())
                            .gamesLost(0)
                            .gamesWon(0)
                            .gamesDrew(0).build();
            teamRepository.save(teamDao);
        }
        team.createTeamPlayers(teamRepository.findByName(team.getTeamName()).getPlayers());
        persistPlayersInTeam(team);
    }

    private void setMatchPlayerDetails(Team team,Long gameId) {
        for (Player player: team.getPlayers()){
            gamePlayerDetailsRepository.save(GamePlayerDetails.builder().
                    playerId(player.getId()).
                    gameId(gameId).
                    teamName(team.getTeamName()).build());
        }
    }
    //Generate ID if player is just created
    private void persistPlayersInTeam(Team team)
    {
        for (Player player: team.getPlayers()) {
            if(player.getId()==null)
                persistNewPlayer(player, team.getTeamName());
        }
    }
    //Udate player and playerstats of a team
    private void updatePlayersAndPlayerStatsInTeam(Team team,Long id)
    {
        for (Player player: team.getPlayers()){
            updatePlayer(player, team.getTeamName());
        }
        for (Player player: team.getPlayers()){
            updatePlayerStats(player,id);
        }
    }
    //Update player and playerstats of a game
    private void updatePlayerAndPlayerStats(Game game)
    {
        updatePlayersAndPlayerStatsInTeam(game.getBattingTeam(),game.getId());
        updatePlayersAndPlayerStatsInTeam(game.getBowlingTeam(),game.getId());
    }
    //Save all details of the Game
    @Override
    public void persistGameOnCompletion(Game game) {
        updatePlayerAndPlayerStats(game);
        GameDao gameDao = gameRepository.findById(game.getId()).get();
        gameDao.setEndDate(new Timestamp(System.currentTimeMillis()));
        gameDao.setGameActive(false);
        gameRepository.save(gameDao);
        updateTeam(game.getBattingTeam(),game.getBowlingTeam());
        updateTeamStats(game.getBowlingTeam(),game.getBattingTeam().getBattingOvers(),game.getId());
        updateTeamStats(game.getBattingTeam(),game.getBowlingTeam().getBattingOvers(),game.getId());
        updatePlayerAndPlayerStats(game);
    }
    //Update wins and draws of the teams
    private void updateTeam(Team team1,Team team2)
    {
        TeamDao teamDao1 = teamRepository.findByName(team1.getTeamName());
        TeamDao teamDao2 = teamRepository.findByName(team2.getTeamName());
        if(team1.getScore()>team2.getScore()) {
            teamDao1.setGamesWon(teamDao1.getGamesWon()+1);
            teamDao2.setGamesLost(teamDao2.getGamesLost()+1);
        }
        else if(team1.getScore()<team2.getScore()){
            teamDao1.setGamesLost(teamDao1.getGamesLost()+1);
            teamDao2.setGamesWon(teamDao2.getGamesWon()+1);
        }
        else{
            teamDao1.setGamesDrew(teamDao1.getGamesDrew()+1);
            teamDao2.setGamesDrew(teamDao2.getGamesDrew()+1);
        }
        teamRepository.save(teamDao1);
        teamRepository.save(teamDao2);
    }
    //Teams stats updated on game over condition
    private void updateTeamStats(Team team, Overs bowlingOvers,Long gameId){
        teamStatsRepository.save(TeamStatsDao.builder().teamName(team.getTeamName())
                .gameId(gameId)
                .score(team.getScore())
                .battingOvers(team.getBattingOvers().getOvers()).
                wicketsTaken(team.getWicketsLost()).
                bowlingovers(bowlingOvers.getOvers()).
                build());
    }
    // creating and assigning ID to new Player
    @Override
    public void persistNewPlayer(Player player,String teamName) {
            PlayerDao playerDao = PlayerDao.builder().
                    teamName(teamName).
                    ballsBowled(player.oversBowled.getNumberOfBalls()).
                    ballsFaced(player.getBallsFaced()).
                    runsScored(player.getRunsScored()).
                    runsGiven(player.getRunsGiven()).
                    wicketsTaken(player.getWicketsTaken()).
                    name(player.getPlayerName()).
                    playerType(player.playerType()).build();
            playerRepository.save(playerDao);
            player.setId(playerDao.getId());

    }

    @Override
    public Game reloadGame(Long gameId) {
        GameDao gameDao = gameRepository.findById(gameId).get();
        return reloadGame(gameDao);
    }

    @Override
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

    private List<PlayerDao> getPlayers(Long gameId,String teamName){
        List<PlayerDao> playerDaoList = new ArrayList<>();
        List<GamePlayerDetails> gamePlayerDetailsList = gamePlayerDetailsRepository.findByGameIdAndTeamNameOrderByIdAsc(gameId,teamName);
        for (GamePlayerDetails gamePlayerDetails:gamePlayerDetailsList) {
            playerDaoList.add(gamePlayerDetails.getPlayerDao());
        }
        return  playerDaoList;
    }

    // Updating player
    private void updatePlayer(Player player,String teamName){
        PlayerDao playerDao = playerRepository.findById(player.getId()).get();
        playerDao.setTeamName(teamName);
        playerDao.setBallsBowled(playerDao.getBallsBowled()+player.getOversBowled().getNumberOfBalls());
        playerDao.setBallsFaced(playerDao.getBallsFaced()+player.getBallsFaced());
        playerDao.setRunsScored(playerDao.getRunsScored()+player.getRunsScored());
        playerDao.setRunsGiven(playerDao.getRunsGiven()+player.getRunsGiven());
        playerDao.setWicketsTaken(playerDao.getWicketsTaken()+player.getWicketsTaken());
        playerRepository.save(playerDao);
    }
    // Updating player Stats
    private void updatePlayerStats(Player player,Long gameId) {
        PlayerStatsCompositeKey playerStatsCompositeKey = new PlayerStatsCompositeKey();
        playerStatsCompositeKey.setPlayerId(player.getId());
        playerStatsCompositeKey.setGameId(gameId);
        PlayerStatsDao playerStatsDao = new PlayerStatsDao();
        playerStatsDao.setPlayerStatsCompositeKey(playerStatsCompositeKey);
        playerStatsDao.setBallsBowled(player.getOversBowled().getNumberOfBalls());
        playerStatsDao.setBallsFaced(player.getBallsFaced());
        playerStatsDao.setWicketsTaken(player.getWicketsTaken());
        playerStatsDao.setRunsScored(player.getRunsScored());
        playerStatsDao.setRunsGiven(player.getRunsGiven());
        playerStatsRepository.save(playerStatsDao);
    }
    //Persisting Each ball data
    @Override
    public void persistBallData(Game game) {
        ballDataRepository.save(BallDataDao.builder().gameId(game.getId()).innings(game.getInnings()).overs(game.getOvers().getOversInFloat()).
                ballOutCome(game.getRun()).
                bowlerId(game.getBowlingTeam().getBowler().getId()).
                batsmanId(game.getBattingTeam().getBatsmanOnStrike().getId()).
                teamScore(game.getBattingTeam().getScore())
                .build());
    }
}

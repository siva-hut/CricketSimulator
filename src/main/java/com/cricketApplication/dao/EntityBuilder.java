package com.cricketApplication.dao;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.entities.*;

import java.sql.Timestamp;
import java.util.Date;

public class EntityBuilder {
    public static GameDao buildGameDao(Game game, Date date, boolean gameActive){
        return  GameDao.builder()
                .firstBattingTeamName(game.getBattingTeam().getTeamName())
                .firstBowlingTeamName(game.getBowlingTeam().getTeamName())
                .totalOvers(game.getOvers().getTotalOvers())
                .startDate(new Timestamp(date.getTime()))
                .gameActive(gameActive)
                .build();
    }
    public static BallDataDao buildBallDataDao(Game game){
        return BallDataDao.builder()
                .gameId(game.getId())
                .innings(game.getInnings())
                .overs(game.getOvers().getOversInFloat())
                .ballOutCome(game.getRun())
                .bowlerId(game.getBowlingTeam().getBowler().getId())
                .batsmanId(game.getBattingTeam().getBatsmanOnStrike().getId())
                .build();
    }
    public static GamePlayerDetails buildGamePlayerDetails(Long playerId,Long gameId,String teamName){
        return GamePlayerDetails.builder().
                playerId(playerId).
                gameId(gameId).
                teamName(teamName).build();
    }
    public static TeamStatsDao buildTeamStatsDao(Team team, Long gameId,String overs){
        return TeamStatsDao.builder().teamName(team.getTeamName())
                .gameId(gameId)
                .score(team.getScore())
                .battingOvers(team.getBattingOvers().getOvers()).
                wicketsTaken(team.getWicketsLost()).
                bowlingovers(overs).
                build();
    }
    public static PlayerStatsDao buildPlayerStatsDao(Player player,Long gameId){
        return PlayerStatsDao.builder()
                .playerStatsCompositeKey(getPlayerStatsCompositeKey(player, gameId))
                .ballsBowled(player.getOversBowled().getNumberOfBalls())
                .ballsFaced(player.getBallsFaced())
                .wicketsTaken(player.getWicketsTaken())
                .runsScored(player.getRunsScored())
                .runsGiven(player.getRunsGiven())
                .build();
    }
    private static PlayerStatsCompositeKey getPlayerStatsCompositeKey(Player player, Long gameId) {
        PlayerStatsCompositeKey playerStatsCompositeKey = new PlayerStatsCompositeKey();
        playerStatsCompositeKey.setPlayerId(player.getId());
        playerStatsCompositeKey.setGameId(gameId);
        return playerStatsCompositeKey;
    }
    public static TeamDao buildTeamDao(String teamName){
        return TeamDao.builder()
                .name(teamName)
                .gamesLost(0)
                .gamesWon(0)
                .gamesDrew(0).build();
    }
    public static PlayerDao buildPlayerDao(String name,String playerType,String teamName){
        return PlayerDao.builder()
                .name(name)
                .playerType(playerType)
                .teamName(teamName)
                .build();
    }
}

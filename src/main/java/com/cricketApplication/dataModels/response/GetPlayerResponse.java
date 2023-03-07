package com.cricketApplication.dataModels.response;

import com.cricketApplication.dao.entities.PlayerDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetPlayerResponse {
    String status;
    String message;
    Long playerId;
    String playerName;
    String teamName;
    int ballsFaced;
    int runsScored;
    int runsGiven;
    int ballsBowled;
    int wicketsTaken;
    String playerType;

    public static GetPlayerResponse getPlayerResponse(PlayerDao playerDao){
        return   GetPlayerResponse.builder()
                .status("success")
                .message("got the player with id: "+playerDao.getId())
                .playerId(playerDao.getId())
                .ballsBowled(playerDao.getBallsBowled()).ballsFaced(playerDao.getBallsFaced())
                .playerName(playerDao.getName()).playerType(playerDao.getPlayerType())
                .runsGiven(playerDao.getRunsGiven()).runsScored(playerDao.getRunsScored()).wicketsTaken(playerDao.getWicketsTaken())
                .teamName(playerDao.getTeamName())
                .build();
    }
}

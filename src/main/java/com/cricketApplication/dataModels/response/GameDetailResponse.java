package com.cricketApplication.dataModels.response;

import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.entities.TeamStatsDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameDetailResponse {
    String status;
    String message;
    Long gameId;
    String team1Name;
    String team2Name;
    int team1score;
    String team1battingOvers;
    int team1wicketsTaken;
    String team1bowlingovers;
    int team2score;
    String team2battingOvers;
    int team2wicketsTaken;
    String team2bowlingovers;

    public static GameDetailResponse getGameDetailResponse(GameDao gameDao, TeamStatsDao team1, TeamStatsDao team2){
        return GameDetailResponse.builder().gameId(gameDao.getId())
                .status("success")
                .message("Game results")
                .team1Name(team1.getTeamName())
                .team1battingOvers(team1.getBattingOvers())
                .team1bowlingovers(team1.getBowlingovers())
                .team1wicketsTaken(team1.getWicketsTaken())
                .team1score(team1.getScore())
                .team2Name(team2.getTeamName())
                .team2battingOvers(team2.getBattingOvers())
                .team2bowlingovers(team2.getBowlingovers())
                .team2wicketsTaken(team2.getWicketsTaken())
                .team2score(team2.getScore()).build();
    }

}

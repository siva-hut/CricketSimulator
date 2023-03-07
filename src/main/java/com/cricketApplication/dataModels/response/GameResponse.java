package com.cricketApplication.dataModels.response;

import com.cricketApplication.dao.entities.GameDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameResponse {
    String status;
    String message;
    Long gameId;
    String firstBattingTeamName;
    String firstBowlingTeamName;
    int totalOvers;
    String startTime;
    String endTime;
    public static GameResponse getGameResponse(GameDao gameDao){
        return GameResponse.builder().
                gameId(gameDao.getId()).
                startTime(gameDao.getStartDate().toString()).
                firstBattingTeamName(gameDao.getFirstBattingTeamName()).
                firstBowlingTeamName(gameDao.getFirstBowlingTeamName()).
                totalOvers(gameDao.getTotalOvers()).
                build();
    }
}

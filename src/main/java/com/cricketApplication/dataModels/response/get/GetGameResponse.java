package com.cricketApplication.dataModels.response.get;

import com.cricketApplication.dao.entities.GameDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetGameResponse {
    String status;
    String message;
    Long gameId;
    String firstBattingTeamName;
    String firstBowlingTeamName;
    int totalOvers;
    String startTime;
    String endTime;
    public static GetGameResponse getGameResponse(GameDao gameDao){
        return GetGameResponse.builder().
                status("success").
                message("game detail").
                gameId(gameDao.getId()).
                startTime(gameDao.getStartDate().toString()).
                endTime(gameDao.getEndDate().toString()).
                firstBattingTeamName(gameDao.getFirstBattingTeamName()).
                firstBowlingTeamName(gameDao.getFirstBowlingTeamName()).
                totalOvers(gameDao.getTotalOvers()).
                build();
    }
}

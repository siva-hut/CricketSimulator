package com.circketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameResponse {
    Long gameId;
    String firstBattingTeamName;
    String firstBowlingTeamName;
    int totalOvers;
    String startTime;
    String endTime;
}

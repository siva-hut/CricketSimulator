package com.cricketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerResponse {
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
}

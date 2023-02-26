package com.circketApplication.dataModels.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGameRequest {
    String firstBattingTeamName;
    String firstBowlingTeamName;
    int totalOvers;
}

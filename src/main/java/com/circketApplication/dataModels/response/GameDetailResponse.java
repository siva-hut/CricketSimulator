package com.circketApplication.dataModels.response;

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

}

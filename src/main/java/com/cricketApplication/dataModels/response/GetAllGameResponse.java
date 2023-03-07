package com.cricketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GetAllGameResponse {
    String status;
    String message;
    List<GameResponse> gameList;

    public static GetAllGameResponse getAllGameResponse(List<GameResponse>gameResponseList){
        return GetAllGameResponse.builder()
                .gameList(gameResponseList)
                .status("success")
                .message("List of games")
                .build();
    }
}

package com.cricketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GetAllPlayerResponse {
    String status;
    String message;
    List<GetPlayerResponse> playerResponseList;
    public static GetAllPlayerResponse getAllPlayerResponse(List<GetPlayerResponse> playerResponseList){
        return GetAllPlayerResponse.builder()
                .status("success")
                .message("Got all the player")
                .playerResponseList(playerResponseList)
                .build();
    }
}

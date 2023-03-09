package com.cricketApplication.dataModels.response.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreatePlayerResponse {
    String status;
    String message;
    Long playerId;

    public static CreatePlayerResponse createPlayerResponse(Long playerId){
        return CreatePlayerResponse.builder()
                .status("success")
                .message("Created Player")
                .playerId(playerId)
                .build();
    }

}

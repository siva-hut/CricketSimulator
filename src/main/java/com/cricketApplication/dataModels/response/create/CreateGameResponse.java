package com.cricketApplication.dataModels.response.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateGameResponse {
    String status;
    String message;
    Long gameId;
    int totalOvers;

    public static CreateGameResponse createGameResponse(String message,Long gameId){
        return CreateGameResponse.builder().
                status("success").
                message(message).
                gameId(gameId)
                .build();
    }
}

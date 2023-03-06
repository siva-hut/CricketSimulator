package com.cricketApplication.dataModels.response;

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

}

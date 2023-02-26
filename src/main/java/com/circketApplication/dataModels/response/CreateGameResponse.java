package com.circketApplication.dataModels.response;

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
}

package com.circketApplication.dataModels.response;

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
}

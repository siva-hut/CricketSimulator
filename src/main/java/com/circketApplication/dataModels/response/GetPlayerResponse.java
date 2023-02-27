package com.circketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class GetPlayerResponse {
    String status;
    String message;
    List<PlayerResponse> playerResponseList;
}

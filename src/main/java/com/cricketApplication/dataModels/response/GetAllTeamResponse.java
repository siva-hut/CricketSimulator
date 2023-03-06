package com.cricketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetAllTeamResponse {
    String status;
    String message;
    List<String> teamNames;
}

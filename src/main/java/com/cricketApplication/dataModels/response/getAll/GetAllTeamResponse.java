package com.cricketApplication.dataModels.response.getAll;

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
    public static GetAllTeamResponse getAllTeamResponse(List<String> teamNames){
        return GetAllTeamResponse.builder()
                .status("success").message("List of teams")
                .teamNames(teamNames)
                .build();
    }
}

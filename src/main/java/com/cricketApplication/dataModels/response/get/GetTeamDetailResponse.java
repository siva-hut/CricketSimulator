package com.cricketApplication.dataModels.response.get;

import com.cricketApplication.dao.entities.TeamDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class GetTeamDetailResponse {
    String status;
    String message;
    TeamDao team;

    public static GetTeamDetailResponse getTeamDetailResponse(TeamDao teamDao){
        return GetTeamDetailResponse.builder().
                status("success").message("Team details").
                team(teamDao).build();
    }
}

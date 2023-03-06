package com.cricketApplication.dataModels.response;

import com.cricketApplication.dao.entities.TeamDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class TeamDetailResponse {
    String status;
    String message;
    TeamDao team;
}

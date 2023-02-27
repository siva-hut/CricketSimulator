package com.circketApplication.dataModels.response;

import com.circketApplication.dao.entities.TeamDao;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

package com.circketApplication.dataModels.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateGameRequest {
    String firstBattingTeamName;
    String firstBowlingTeamName;
    int totalOvers;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss",timezone = "IST")
    public Date startDate;
}

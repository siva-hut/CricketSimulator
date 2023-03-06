package com.cricketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class BallDetailResponse {
    int innings;
    char ballOutCome;
    Long batsmanId;
    String batsmanName;
    Long bowlerId;
    String bowlerName;
    float overs;
}

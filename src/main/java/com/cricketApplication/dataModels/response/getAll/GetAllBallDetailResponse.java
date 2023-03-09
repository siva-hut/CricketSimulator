package com.cricketApplication.dataModels.response.getAll;

import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dataModels.response.get.GetBallDetailResponse;
import com.cricketApplication.dataModels.response.get.GetPlayerResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Builder
@Getter
@Setter
public class GetAllBallDetailResponse {
    String status;
    String message;
    List<GetBallDetailResponse> ballDetailResponseList;
    public static GetAllBallDetailResponse getAllBallDetailResponse(List<BallDataDao> ballDataDaoList){
        return GetAllBallDetailResponse.builder()
                .status("Success")
                .message("got all the ball details")
                .ballDetailResponseList(convert(ballDataDaoList))
                .build();
    }
    private static List<GetBallDetailResponse> convert(List<BallDataDao> ballDataDaoList) {
        List<GetBallDetailResponse> ballDetailResponses = new ArrayList<>();
        for (BallDataDao ballDataDao : ballDataDaoList) {
            ballDetailResponses.add(GetBallDetailResponse.getBallDetailResponse(ballDataDao));
        }
        return ballDetailResponses;
    }
}

package com.cricketApplication.dataModels.response.get;

import com.cricketApplication.dao.entities.BallDataDao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class GetBallDetailResponse {
    int innings;
    char ballOutCome;
    Long batsmanId;
    String batsmanName;
    Long bowlerId;
    String bowlerName;
    float overs;

    public static GetBallDetailResponse getBallDetailResponse(BallDataDao ballDataDao){
        GetBallDetailResponse getBallDetailResponse =  GetBallDetailResponse.builder().
                ballOutCome(ballDataDao.getBallOutCome()).
                overs(ballDataDao.getOvers()).
                batsmanId(ballDataDao.getBatsmanId()).
                bowlerId(ballDataDao.getBowlerId()).
                innings(ballDataDao.getInnings()).
                build();
        if(ballDataDao.getBatsman()!=null)
            getBallDetailResponse.setBatsmanName(ballDataDao.getBatsman().getName());
        if(ballDataDao.getBowler()!=null)
            getBallDetailResponse.setBowlerName(ballDataDao.getBowler().getName());
        return getBallDetailResponse;
    }
}

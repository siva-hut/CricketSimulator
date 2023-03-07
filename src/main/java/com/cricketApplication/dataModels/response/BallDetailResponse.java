package com.cricketApplication.dataModels.response;

import com.cricketApplication.dao.entities.BallDataDao;
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

    public static BallDetailResponse getBallDetailResponse(BallDataDao ballDataDao){
        return BallDetailResponse.builder().
                ballOutCome(ballDataDao.getBallOutCome()).
                overs(ballDataDao.getOvers()).
                batsmanId(ballDataDao.getBatsmanId()).
                batsmanName(ballDataDao.getBatsman().getName()).
                bowlerId(ballDataDao.getBowlerId()).
                bowlerName(ballDataDao.getBowler().getName()).
                innings(ballDataDao.getInnings()).
                build();
    }
}

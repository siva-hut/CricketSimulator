package com.circketApplication.service.impl.dataService;

import com.circketApplication.dao.entities.BallDataDao;
import com.circketApplication.dao.repositories.BallDataRepository;
import com.circketApplication.dataModels.response.BallDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BallDetailService {
    @Autowired
    private BallDataRepository ballDataRepository;
    private List<BallDetailResponse> convert(List<BallDataDao> ballDataDaoList){
        List<BallDetailResponse> ballDetailResponseList = new ArrayList<>();
        for (BallDataDao ballDataDao:ballDataDaoList) {
            ballDetailResponseList.add(
                    BallDetailResponse.builder().
                            ballOutCome(ballDataDao.getBallOutCome()).overs(ballDataDao.getOvers())
                            .batsmanId(ballDataDao.getBatsmanId()).batsmanName(ballDataDao.getBatsman().getName())
                            .bowlerId(ballDataDao.getBowlerId()).bowlerName(ballDataDao.getBowler().getName())
                            .innings(ballDataDao.getInnings())
                            .build()
            );
        }
        return ballDetailResponseList;
    }
    public List<BallDetailResponse> getAllBallDetails(Long gameId)
    {
        return convert(ballDataRepository.findByGameId(gameId));
    }
    public List<BallDetailResponse> getOverDetails(Long gameId,int over){
        float startRange = over;
        float endRange = over+1;
        return convert(ballDataRepository.findByOversBetweenAndGameId(startRange,endRange,gameId));
    }
    public List<BallDetailResponse> getOverDetailsByRange(Long gameId,float startRange,float endRange){
        return convert(ballDataRepository.findByOversBetweenAndGameId(startRange,endRange,gameId));
    }
}

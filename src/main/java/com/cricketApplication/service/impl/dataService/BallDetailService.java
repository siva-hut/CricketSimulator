package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.repositories.BallDataRepository;
import com.cricketApplication.dataModels.response.BallDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BallDetailService {
    @Autowired
    private BallDataRepository ballDataRepository;

    //Converting BallDataDao to BallDataResponse
    private List<BallDetailResponse> convert(List<BallDataDao> ballDataDaoList) {
        List<BallDetailResponse> ballDetailResponseList = new ArrayList<>();
        for (BallDataDao ballDataDao : ballDataDaoList) {
            ballDetailResponseList.add(BallDetailResponse.getBallDetailResponse(ballDataDao));
        }
        return ballDetailResponseList;
    }

    public List<BallDetailResponse> getAllBallDetails(Long gameId) {
        return convert(ballDataRepository.findByGameId(gameId));
    }

    public List<BallDetailResponse> getOverDetails(Long gameId, int over) {
        float startRange = over;
        float endRange = over + 1;
        return convert(
                ballDataRepository.findByOversBetweenAndGameId(startRange, endRange, gameId)
        );
    }

    public List<BallDetailResponse> getOverDetailsByRange(Long gameId, float startRange, float endRange) {
        return convert(
                ballDataRepository.findByOversBetweenAndGameId(startRange, endRange, gameId)
        );
    }
}

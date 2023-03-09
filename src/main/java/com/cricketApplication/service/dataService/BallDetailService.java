package com.cricketApplication.service.dataService;

import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.repositories.BallDataRepository;
import com.cricketApplication.dataModels.response.get.GetBallDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BallDetailService {
    @Autowired
    private BallDataRepository ballDataRepository;
    private List<GetBallDetailResponse> convert(List<BallDataDao> ballDataDaoList) {
        List<GetBallDetailResponse> ballDetailResponseList = new ArrayList<>();
        for (BallDataDao ballDataDao : ballDataDaoList) {
            ballDetailResponseList.add(GetBallDetailResponse.getBallDetailResponse(ballDataDao));
        }
        return ballDetailResponseList;
    }

    public List<GetBallDetailResponse> getAllBallDetails(Long gameId) {
        return convert(ballDataRepository.findByGameId(gameId));
    }

    public List<GetBallDetailResponse> getOverDetails(Long gameId, int over) {
        float startRange = over;
        float endRange = over + 1;
        return convert(
                ballDataRepository.findByOversBetweenAndGameId(startRange, endRange, gameId)
        );
    }

    public List<GetBallDetailResponse> getOverDetailsByRange(Long gameId, float startRange, float endRange) {
        return convert(
                ballDataRepository.findByOversBetweenAndGameId(startRange, endRange, gameId)
        );
    }
}

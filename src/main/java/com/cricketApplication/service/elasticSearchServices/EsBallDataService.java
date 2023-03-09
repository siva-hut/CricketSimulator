package com.cricketApplication.service.elasticSearchServices;

import com.cricketApplication.dao.elasticSearchRepository.ElasticBallDataRepository;
import com.cricketApplication.dataModels.response.get.GetBallDetailResponse;
import com.cricketApplication.dataModels.response.getAll.GetAllBallDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsBallDataService {
    @Autowired
    private ElasticBallDataRepository elasticBallDataRepository;
    public GetAllBallDetailResponse getBallDetailsByGameId(Long gameId) {
        return GetAllBallDetailResponse.getAllBallDetailResponse(elasticBallDataRepository.findByGameId(gameId));
    }

}

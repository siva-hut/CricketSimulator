package com.cricketApplication.Controller.elasticSearchControllers;

import com.cricketApplication.dataModels.response.get.GetBallDetailResponse;
import com.cricketApplication.dataModels.response.getAll.GetAllBallDetailResponse;
import com.cricketApplication.service.elasticSearchServices.EsBallDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/es/")
public class EsBallDataController {
    @Autowired
    private EsBallDataService esBallDataService;
    @GetMapping("/getBallDetailsByGameId")
    public GetAllBallDetailResponse getBallDetailsByGameId(@RequestParam Long gameId) {
        return esBallDataService.getBallDetailsByGameId(gameId);
    }
}

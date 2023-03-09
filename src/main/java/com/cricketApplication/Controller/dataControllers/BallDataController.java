package com.cricketApplication.Controller.dataControllers;

import com.cricketApplication.dataModels.response.get.GetBallDetailResponse;
import com.cricketApplication.service.dataService.BallDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BallDataController {
    @Autowired
    private BallDetailService ballDetailService;

    @GetMapping("/getAllBallDetails")
    public List<GetBallDetailResponse> getEntireMatchDetails(@RequestParam Long gameId) {
        return ballDetailService.getAllBallDetails(gameId);
    }

    @GetMapping("/getOverDetails")
    public List<GetBallDetailResponse> getOverDetails(@RequestParam Long gameId, @RequestParam int over) {
        return ballDetailService.getOverDetails(gameId, over);
    }

    @GetMapping("/getSpecificBallDetails")
    public List<GetBallDetailResponse> getSpecificBallDetails(@RequestParam Long gameId, @RequestParam float over) {
        return ballDetailService.getOverDetailsByRange(gameId, over, over);
    }

    @GetMapping("/getBallDetailsWithinRange")
    public List<GetBallDetailResponse> getBallDetailsWithinRange(@RequestParam Long gameId, @RequestParam float startRange, @RequestParam float endRange) {
        return ballDetailService.getOverDetailsByRange(gameId, startRange, endRange);
    }
}

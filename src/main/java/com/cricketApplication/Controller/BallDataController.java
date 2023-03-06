package com.circketApplication.Controller;

import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.dataModels.response.BallDetailResponse;
import com.circketApplication.service.interfaces.GameService;
import com.circketApplication.service.impl.dataService.BallDetailService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private GameService gameService;
    @Autowired
    private BallDetailService ballDetailService;
    @GetMapping("/getAllBallDetails")
    public List<BallDetailResponse> getEntireMatchDetails(@RequestParam Long gameId) {
        return ballDetailService.getAllBallDetails(gameId);
    }
    @GetMapping("/getOverDetails")
    public List<BallDetailResponse> getOverDetails(@RequestParam Long gameId, @RequestParam int over) {
        return ballDetailService.getOverDetails(gameId,over);
    }
    @GetMapping("/getSpecificBallDetails")
    public List<BallDetailResponse> getSpecificBallDetails(@RequestParam Long gameId, @RequestParam float over) {
        return ballDetailService.getOverDetailsByRange(gameId,over,over);
    }
    @GetMapping("/getBallDetailsWithinRange")
    public List<BallDetailResponse> getBallDetailsWithinRange(@RequestParam Long gameId, @RequestParam float startRange,@RequestParam float endRange) {
        return ballDetailService.getOverDetailsByRange(gameId,startRange,endRange);
    }
}

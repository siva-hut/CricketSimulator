package com.circketApplication.Controller;

import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.dao.entities.BallDataDao;
import com.circketApplication.dataModels.response.BallDetailResponse;
import com.circketApplication.service.GameService;
import com.circketApplication.service.impl.BallDetailService;
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
    GameService gameService;
    @Autowired
    BallDetailService ballDetailService;
    @GetMapping("/getAllBallDetails")
    public List<BallDetailResponse> getEntireMatchDetails(@RequestParam Long gameId) {
        return ballDetailService.getAllBallDetails(gameId);
    }
    @GetMapping("/getOverDetails")
    public List<BallDetailResponse> getOverDetails(@RequestParam Long gameId, @RequestParam int over) {
        return ballDetailService.getOverDetails(gameId,over);
    }
    @GetMapping("/getSpecificBallDetails")
    public void getSpecificBallDetails(@RequestParam String gameId, @RequestParam String over) {

    }
    @GetMapping("/getBallDetailsWithinRange")
    public List<BallDetailResponse> getBallDetailsWithinRange(@RequestParam Long gameId, @RequestParam float startrange,@RequestParam float endRange) {
        return ballDetailService.getOverDetailsByRange(gameId,startrange,endRange);
    }
    @GetMapping("/startGame")
    public void startGame() {
        GameBuilder gameBuilder = new GameBuilder();
        gameService.createGame(gameBuilder);
    }
}

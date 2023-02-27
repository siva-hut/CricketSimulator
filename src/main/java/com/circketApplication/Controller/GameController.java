package com.circketApplication.Controller;

import com.circketApplication.dataModels.response.GameDetailResponse;
import com.circketApplication.dataModels.response.GetAllGameResponse;
import com.circketApplication.service.interfaces.GameService;
import com.circketApplication.service.impl.dataService.GameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private GameDetailService gameDetailService;
    @Autowired
    private GameService gameService;
    @GetMapping("/getAllGames")
    public GetAllGameResponse getAllGames() {
        return gameDetailService.getAllGames();
    }
    @GetMapping("/getAllActiveGames")
    public GetAllGameResponse getAllActiveGames() {
        return gameDetailService.getAllActiveGames();
    }
    @GetMapping("/getGameDetails")
    public GameDetailResponse getMatchDetails(@RequestParam Long gameId) {
        return gameDetailService.getGameDetails(gameId);
    }
    @PostMapping("/pauseGame")
    public String pauseGame(@RequestParam Long gameId){
        gameService.pauseGame(gameId);
        return "Game paused";
    }
    @PostMapping("/resumeGame")
    public String resumeGame(@RequestParam Long gameId){
        gameService.resumeGame(gameId);
        return "Game resumed";
    }
}

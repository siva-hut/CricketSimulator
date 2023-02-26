package com.circketApplication.Controller;

import com.circketApplication.dataModels.response.GameDetailResponse;
import com.circketApplication.dataModels.response.GetAllGameResponse;
import com.circketApplication.service.GameService;
import com.circketApplication.service.impl.GameDetailService;
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
    public void pauseGame(@RequestParam Long gameId){
        System.out.println("TF");
        gameService.pauseGame(gameId);
    }
}

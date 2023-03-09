package com.cricketApplication.Controller.dataControllers;

import com.cricketApplication.dataModels.response.get.GetGameDetailResponse;
import com.cricketApplication.dataModels.response.getAll.GetAllGameResponse;
import com.cricketApplication.service.dataService.GameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameDataController {
    @Autowired
    private GameDetailService gameDetailService;

    @GetMapping("/getAllGames")
    public GetAllGameResponse getAllGames() {
        return gameDetailService.getAllGames();
    }

    @GetMapping("/getAllActiveGames")
    public GetAllGameResponse getAllActiveGames() {
        return gameDetailService.getAllActiveGames();
    }

    @GetMapping("/getGameDetails")
    public GetGameDetailResponse getMatchDetails(@RequestParam Long gameId) {
        return gameDetailService.getGameDetails(gameId);
    }

}

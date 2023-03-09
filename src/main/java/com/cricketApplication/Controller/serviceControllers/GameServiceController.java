package com.cricketApplication.Controller.serviceControllers;

import com.cricketApplication.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameServiceController {

    @Autowired
    private GameService gameService;
    @PostMapping("/pauseGame")
    public String pauseGame(@RequestParam Long gameId) {
        gameService.pauseGame(gameId);
        return "Game paused";
    }

    @PostMapping("/resumeGame")
    public String resumeGame(@RequestParam Long gameId) {
        gameService.resumeGame(gameId);
        return "Game resumed";
    }
}

package com.circketApplication.Controller;

import com.circketApplication.dataModels.response.GetPlayerResponse;
import com.circketApplication.dataModels.response.PlayerResponse;
import com.circketApplication.service.impl.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/getAllPlayers")
    public GetPlayerResponse getAllPlayers() {
        return playerService.getAllPlayers();
    }
    @GetMapping("/getPlayer")
    public GetPlayerResponse getPlayerDetails(@RequestParam Long playerId){
        return playerService.getPlayer(playerId);
    }
    @GetMapping("/thread")
    public void test() throws InterruptedException {
        playerService.test();
    }
}

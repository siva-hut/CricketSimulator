package com.circketApplication.Controller;

import com.circketApplication.dataModels.response.GetPlayerResponse;
import com.circketApplication.service.impl.dataService.PlayerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlayerController {
    @Autowired
    private PlayerDetailService playerDetailService;
    @GetMapping("/getAllPlayers")
    public GetPlayerResponse getAllPlayers() {
        return playerDetailService.getAllPlayers();
    }
    @GetMapping("/getPlayer")
    public GetPlayerResponse getPlayerDetails(@RequestParam Long playerId){
        return playerDetailService.getPlayer(playerId);
    }
    @GetMapping("/thread")
    public void test() throws InterruptedException {
        playerDetailService.test();
    }
}

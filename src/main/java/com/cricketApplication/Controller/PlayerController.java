package com.cricketApplication.Controller;

import com.cricketApplication.dataModels.response.GetAllPlayerResponse;
import com.cricketApplication.dataModels.response.GetPlayerResponse;
import com.cricketApplication.service.impl.dataService.PlayerDetailService;
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
    public GetAllPlayerResponse getAllPlayers() {
        return playerDetailService.getAllPlayers();
    }

    @GetMapping("/getPlayer")
    public GetPlayerResponse getPlayerDetails(@RequestParam Long playerId) {
        return playerDetailService.getPlayer(playerId);
    }

}
